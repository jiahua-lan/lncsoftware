package cn.lncsa.controller;

import cn.lncsa.common.ResultObject;
import cn.lncsa.data.model.article.Article;
import cn.lncsa.data.model.article.ArticleBody;
import cn.lncsa.data.model.article.ArticleStatus;
import cn.lncsa.data.model.article.Tag;
import cn.lncsa.services.IArticleServices;
import cn.lncsa.services.ICommitServices;
import cn.lncsa.services.ITaggingServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import java.lang.reflect.InvocationTargetException;
import java.util.List;

/**
 * Created by cattenlinger on 2016/10/6.
 */
@Controller
@RequestMapping("/articles/modify")
public class ArticleModifyingController {

    private static int FLAG_MODIFIED_NOTHING = 0;//0000
    private static int FLAG_MODIFIED_ARTICLE = 1;//0001
    private static int FLAG_MODIFIED_CONTENT = 2;//0010
    private static int FLAG_MODIFIED_TAGS    = 4;//0100

    private IArticleServices articleServices;
    private ITaggingServices taggingServices;
    private ICommitServices commitServices;

    @Autowired
    public void setArticleServices(IArticleServices articleServices) {
        this.articleServices = articleServices;
    }

    @Autowired
    public void setTaggingServices(ITaggingServices taggingServices) {
        this.taggingServices = taggingServices;
    }

    @Autowired
    public void setCommitServices(ICommitServices commitServices) {
        this.commitServices = commitServices;
    }

    /*
    *
    * Methods
    *
    * */

    // /articles/modify/new
    // ArticleObject
    // tag={int}...
    public Object newArticle(Article article, String content, List<Integer> tags) {
        ArticleBody articleBody = new ArticleBody();
        articleBody.setContent(content);
        articleServices.save(article, articleBody);
        if (!taggingArticle(article, tags)) return new ResultObject(true, "tagging error");
        return new ResultObject(true);
    }

    // /articles/modify/save
    // ArticleObject
    // content={string}
    // tag={int}
    // modifyFlag={int}
    public Object saveArticle(Article article, String content, List<Integer> tagIds, int modifyFlag) {
        //Check if any changing flags, if has then action.
        if ((modifyFlag | FLAG_MODIFIED_NOTHING) != FLAG_MODIFIED_NOTHING) {
            //If this article has no id, it may be not exist, reject to save.
            if (article.getId() != null) {
                boolean emptyContent = false;
                ArticleBody articleBody = null;

                //Modifying of article content should be handled first.
                if ((modifyFlag & FLAG_MODIFIED_CONTENT) == FLAG_MODIFIED_CONTENT) {
                    articleBody = new ArticleBody();
                    //If the article has content, save the article body object id as the article content's id set,
                    //and fill article body content using income-argument, it may more effective than get an object from
                    //database and modifying it.
                    if (article.getContentId() != null) articleBody.setId(article.getContentId());
                    else emptyContent = true;
                    articleBody.setContent(content);

                    //Save contents
                    articleServices.saveBody(articleBody);
                }

                //Modifying of article head should be handle secondary.Procedure at front known if article has content
                //or not (article's contentId is null or not).
                if ((modifyFlag & FLAG_MODIFIED_ARTICLE) == FLAG_MODIFIED_ARTICLE) {
                    //If the article has empty content, binding the content that saved currently to it.
                    if (emptyContent) article.setContentId(articleBody.getId());

                    //After this action, all changes of article and it's body were saved.
                    articleServices.saveHead(article);
                }

                //Tagging article can put to latest.
                if ((modifyFlag & FLAG_MODIFIED_TAGS) == FLAG_MODIFIED_TAGS) {
                    if (!taggingArticle(article, tagIds)) return new ResultObject(false, "internal error");
                }
            }
        }
        return new ResultObject(true);
    }

    // /articles/modify/delete/{articleId}
    // force={boolean}
    public Object deleteArticle(Integer articleId, boolean force) {
        Article article = articleServices.get(articleId);
        if (article == null) return new ResultObject(false, "article not found");

        //If "force" flag not on, will only set article to "recycle" status
        if (!force) {
            article.setStatus(ArticleStatus.Recycled);
            articleServices.saveHead(article);
            return new ResultObject(true);
        } else {
            taggingServices.removeAllTagsFromArticle(articleId);
            commitServices.deleteAllByArticleId(articleId);
        }

        return new ResultObject(true, "deleted all things about this article");
    }

    /*
    *   Private procedures
    * */

    private boolean taggingArticle(Article article, List<Integer> tagIds) {
        if (tagIds != null && tagIds.size() != 0) {
            try {
                //Get tags from database and remove or add tags to it by diff-list.
                List<Tag> tagList = taggingServices.get(tagIds);
                taggingServices.taggingArticle(article.getId(), tagList);
            } catch (NoSuchMethodException | InstantiationException | InvocationTargetException | IllegalAccessException e) {
                return false;
            }
        }
        return true;
    }
}
