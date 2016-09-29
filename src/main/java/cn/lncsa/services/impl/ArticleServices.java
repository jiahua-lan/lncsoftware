package cn.lncsa.services.impl;

import cn.lncsa.common.ListTools;
import cn.lncsa.data.dao.article.ITagArticleDAO;
import cn.lncsa.data.model.article.ArticleTag;
import cn.lncsa.data.dao.article.IArticleDAO;
import cn.lncsa.data.model.article.Article;
import cn.lncsa.data.model.article.Tag;
import cn.lncsa.services.IArticleServices;
import cn.lncsa.services.helper.RelationshipHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.lang.reflect.InvocationTargetException;
import java.util.*;

/**
 * Created by catten on 16/7/1.
 */
@Service
public class ArticleServices implements IArticleServices {

    private IArticleDAO articleDAO;
    private ITagArticleDAO tagArticleDAO;

    private RelationshipHelper<Article,Tag,ArticleTag> relationshipHelper;

    public ArticleServices(){
        relationshipHelper = new RelationshipHelper<>();
    }

    @Autowired
    public void setArticleDAO(IArticleDAO articleDAO) {
        this.articleDAO = articleDAO;
    }

    @Autowired
    public void setTagArticleDAO(ITagArticleDAO tagArticleDAO) {
        this.tagArticleDAO = tagArticleDAO;
    }

    @Override
    public void saveArticle(Article article, List<Tag> tags) throws NoSuchMethodException, InstantiationException, IllegalAccessException, InvocationTargetException {

        boolean newArticle = (article.getId() == null);
        articleDAO.save(article);
        //If article is a new article, it will have no tag refer to it
        if (newArticle) taggingArticle(article, tags);
            //If not , may have some existed relationships, we just need to update different parts
        else relationshipHelper.updateRelationship(
                article,
                ListTools.listDiff(tagArticleDAO.getByArticle(article), tags), //Compare the origin tagList and new tagList
                tagArticleDAO
        );
    }

    @Override
    public void deleteArticle(Integer articleId) {

        articleDAO.delete(articleId);
    }

    @Override
    public Page<Article> getAllArticle(Pageable pageable, String... status) {
        return articleDAO.findAll(status, pageable);
    }

    @Override
    public Page<Article> getArticleByTag(Tag tag, Pageable pageable, String... status) {
        return tagArticleDAO.findArticleByTag(tag, Arrays.asList(status), pageable);
    }

    @Override
    public Page<Article> getArticleByUserId(Integer userId, Pageable pageable, String... status) {
        return articleDAO.findByAuthorId(userId, status, pageable);
    }

    @Override
    public Page<Article> findArticleBetweenDate(Date startDate, Date endDate, Pageable pageable, String... status) {
        return articleDAO.getArticleBetweenCreateDate(startDate, endDate, status, pageable);
    }

    @Override
    public Page<Article> findArticleBetweenModifiedDate(Date startDate, Date endDate, Pageable pageable, String... status) {
        return articleDAO.getArticleBetweenModifiedDate(startDate, endDate, status, pageable);
    }

    @Override
    public Page<Article> findArticleByKeyword(String keyword, Pageable pageable, String... status) {
        return null;
    }

    /*
    *
    * Private procedure
    *
    * */

    //

    private void taggingArticle(Article article, List<Tag> tagList) {
        List<ArticleTag> relations = new LinkedList<>();
        for (Tag tag : tagList) relations.add(new ArticleTag(tag, article));
        tagArticleDAO.save(relations);
    }

}
