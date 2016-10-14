package cn.lncsa.services.impl;

import cn.lncsa.data.dao.article.IArticleBodyDAO;
import cn.lncsa.data.dao.article.ITagArticleDAO;
import cn.lncsa.data.model.article.ArticleBody;
import cn.lncsa.data.model.article.ArticleTag;
import cn.lncsa.data.dao.article.IArticleDAO;
import cn.lncsa.data.model.article.Article;
import cn.lncsa.data.model.article.Tag;
import cn.lncsa.services.IArticleServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * Created by catten on 16/7/1.
 */
@Service
public class ArticleServices implements IArticleServices {

    private IArticleDAO articleDAO;
    private ITagArticleDAO tagArticleDAO;
    private IArticleBodyDAO articleBodyDAO;

    @Autowired
    public void setArticleDAO(@Qualifier("IArticleDAO") IArticleDAO articleDAO) {
        this.articleDAO = articleDAO;
    }

    @Autowired
    public void setTagArticleDAO(@Qualifier("ITagArticleDAO") ITagArticleDAO tagArticleDAO) {
        this.tagArticleDAO = tagArticleDAO;
    }

    @Autowired
    public void setArticleBodyDAO(IArticleBodyDAO articleBodyDAO) {
        this.articleBodyDAO = articleBodyDAO;
    }

    @Override
    public void save(Article article, ArticleBody body){
        boolean isNewArticle = article.getContentId() == null;

        articleBodyDAO.save(body);

        //If article is a new article, it will have no tag refer to it
        if (!isNewArticle) {
            if (!article.getContentId().equals(body.getId())) {
                article.setContentId(body.getId());
                articleDAO.save(article);
            }
        }
        //If not , may have some existed relationships, we just need to update different parts
        else {
            article.setContentId(body.getId());
            articleDAO.save(article);
        }
    }

    @Override
    public void saveHead(Article article) {
        articleDAO.save(article);
    }

    @Override
    public void saveBody(ArticleBody articleBody) {
        articleBodyDAO.save(articleBody);
    }

    @Override
    public void delete(Integer articleId) {
        articleDAO.delete(articleId);
    }

    @Override
    public Article get(Integer articleId) {
        return articleDAO.findOne(articleId);
    }

    @Override
    public Page<Article> get(Pageable pageable, String... status) {
        return articleDAO.findAll(status, pageable);
    }

    @Override
    public List<Article> getLatest(Integer count, String... status) {
        return articleDAO.findAll(status,new PageRequest(0,count,Sort.Direction.DESC)).getContent();
    }

    @Override
    public Page<Article> getByTag(Tag tag, Pageable pageable, String... status) {
        return null;
    }

    @Override
    public Page<Article> getByUserId(Integer userId, Pageable pageable, String... status) {
        return articleDAO.findByAuthorId(userId, status, pageable);
    }

    @Override
    public Page<Article> findBetweenDate(Date startDate, Date endDate, Pageable pageable, String... status) {
        return null;
    }

    @Override
    public Page<Article> findBetweenModifiedDate(Date startDate, Date endDate, Pageable pageable, String... status) {
        return null;
    }

    @Override
    public Page<Article> findByKeyword(String keyword, Pageable pageable, String... status) {
        return null;
    }

    @Override
    public ArticleBody getBody(Integer articleId) {
        return articleBodyDAO.getOne(articleId);
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
