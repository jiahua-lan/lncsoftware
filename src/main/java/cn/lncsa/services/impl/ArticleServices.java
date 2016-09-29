package cn.lncsa.services.impl;

import cn.lncsa.common.ListTools;
import cn.lncsa.data.dao.article.ITagArticleDAO;
import cn.lncsa.data.model.article.ArticleTag;
import cn.lncsa.data.dao.article.IArticleDAO;
import cn.lncsa.data.dao.article.ITagDAO;
import cn.lncsa.data.model.article.Article;
import cn.lncsa.data.model.article.Tag;
import cn.lncsa.services.IArticleServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * Created by catten on 16/7/1.
 */
@Service
public class ArticleServices implements IArticleServices {

    private IArticleDAO articleDAO;
    private ITagArticleDAO tagArticleDAO;

    @Autowired
    public void setArticleDAO(IArticleDAO articleDAO) {
        this.articleDAO = articleDAO;
    }

    @Autowired
    public void setTagArticleDAO(ITagArticleDAO tagArticleDAO) {
        this.tagArticleDAO = tagArticleDAO;
    }

    @Override
    public void saveArticle(Article article, List<Tag> tags) {

        boolean newArticle = (article.getId() == null);
        articleDAO.save(article);
        //If article is a new article, it will have no tags refer to it
        if (newArticle) taggingArticle(article, tags);
            //If not , may have some existed relationships, we just need to update different parts
        else updateTagRelationship(
                article,
                ListTools.listDiff(tagArticleDAO.getByArticle(article), tags) //Compare the origin tagList and new tagList
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
    public Page<Article> getArticleByTags(List<Tag> tags, Pageable pageable, String... status) {
        return tagArticleDAO.findArticleByTags(tags, status, pageable);
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
    private void updateTagRelationship(Article article, List<Tag>[] tagDiff) {
        List<ArticleTag> delList = tagArticleDAO.getRelationships(article.getId(), tagDiff[ListTools.LIST_DELETE]);
        List<ArticleTag> addList = new LinkedList<>();
        for (Tag tag : tagDiff[ListTools.LIST_ADD]) addList.add(new ArticleTag(tag, article));
        tagArticleDAO.delete(delList);
        tagArticleDAO.save(addList);
    }

    private void taggingArticle(Article article, List<Tag> tagList) {
        List<ArticleTag> relations = new LinkedList<>();
        for (Tag tag : tagList) relations.add(new ArticleTag(tag, article));
        tagArticleDAO.save(relations);
    }

}
