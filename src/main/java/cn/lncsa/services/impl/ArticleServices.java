package cn.lncsa.services.impl;

import cn.lncsa.common.exceptions.ArticleOperateException;
import cn.lncsa.data.dao.article.IArticleDAO;
import cn.lncsa.data.dao.article.IArticleTagDAO;
import cn.lncsa.data.dao.article.ICommitDAO;
import cn.lncsa.data.dao.article.ITagDAO;
import cn.lncsa.data.model.article.Article;
import cn.lncsa.data.model.article.ArticleTag;
import cn.lncsa.data.model.article.Tag;
import cn.lncsa.services.IArticleServices;
import org.pegdown.PegDownProcessor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by catten on 16/7/1.
 */
@Service
public class ArticleServices implements IArticleServices {
    private IArticleDAO articleDAO;
    private IArticleTagDAO articleTagDAO;
    private ICommitDAO commitDAO;
    private ITagDAO tagDAO;

    @Autowired
    public void setArticleDAO(IArticleDAO articleDAO) {
        this.articleDAO = articleDAO;
    }

    @Autowired
    public void setArticleTagDAO(IArticleTagDAO articleTagDAO) {
        this.articleTagDAO = articleTagDAO;
    }

    @Autowired
    public void setCommitDAO(ICommitDAO commitDAO) {
        this.commitDAO = commitDAO;
    }

    @Autowired
    public void setTagDAO(ITagDAO tagDAO) {
        this.tagDAO = tagDAO;
    }

    private static PageRequest latestArticlePageRequest = new PageRequest(0, 5, new Sort(Sort.Direction.DESC, "createDate"));

    public Article getArticle(Integer articleId) throws ArticleOperateException {
        Article article = articleDAO.findOne(articleId);
        if(article == null) throw new ArticleOperateException("Target article not exist.");
        return article;
    }

    @Override
    public List<Article> getLatestArticle() {
        return articleDAO.findAll(latestArticlePageRequest).getContent();
    }

    @Override
    @Transactional
    public Article saveArticle(Article article, List<Tag> tags) throws ArticleOperateException {
        //Check if article is a new article.
        if (article.getId() != null) {
            //If try to save a non-exist article then throw an exception
            if (articleDAO.findOne(article.getId()) == null) throw new ArticleOperateException("Article not exist");
            if (tags != null) {
                List<Tag> articleTags = tagDAO.getByArticle(article);
                saveTagList(article, articleTags, tags);
                articleDAO.save(article);
            }
        } else {
            //Save the article before tag it.
            articleDAO.save(article);
            saveTagList(article, tags);
        }
        return article;
    }

    /**
     * Tagging the article
     *
     * @param article    target article
     * @param oldTagList the old tag list
     * @param newTagList new tags for tagging
     * @return
     */
    @Transactional
    private List<Tag> saveTagList(Article article, List<Tag> oldTagList, List<Tag> newTagList) {
        //Check if has tags that not exist in database
        for (Tag tag : newTagList) {
            List<Tag> createTagList = new LinkedList<>();
            if (tag.getId() == null) createTagList.add(tag);
            if (createTagList.size() > 0) tagDAO.save(createTagList);
        }

        //If old tag list not null, clear all tags.
        if (oldTagList != null && oldTagList.size() != 0) tagDAO.delete(tagDAO.getByArticle(article));
        //Re-tagging the article using new tags
        if (newTagList.size() > 0) for (Tag tag : newTagList) articleTagDAO.save(new ArticleTag(tag, article));
        return newTagList;
    }

    /**
     * Tagging the article
     * <p>
     * Only use for tagging an no-tagged article
     *
     * @param article target article
     * @param tagList tags for tagging
     * @return
     */
    private List<Tag> saveTagList(Article article, List<Tag> tagList) {
        return saveTagList(article, null, tagList);
    }

    @Override
    @Transactional
    public Article deleteArticle(Integer articleId) throws ArticleOperateException {
        Article article = getArticle(articleId);
        //Delete commits
        commitDAO.deleteByArticleId(articleId);
        //Delete article-tag relationship
        articleTagDAO.deleteByArticleId(articleId);
        //Delete article
        articleDAO.delete(articleId);
        return article;
    }

    @Override
    public Article setArticleStatus(Integer articleId, String status) throws ArticleOperateException {
        Article article = getArticle(articleId);
        article.setStatus(status);
        return articleDAO.save(article);
    }

    @Override
    public Page<Article> getAllArticle(PageRequest pageRequest, String... status) {
        return articleDAO.findAll(status, pageRequest);
    }

    @Override
    public Page<Article> getArticleByTags(List<Tag> tags, PageRequest pageRequest, String... status) {
        return articleDAO.findArticleByTags(tags, status, pageRequest);
    }

    @Override
    public Page<Article> getArticleByUserId(Integer userId, PageRequest pageRequest, String... status){
        return articleDAO.findByAuthorId(userId,status,pageRequest);
    }

    @Override
    public Page<Article> findArticleBetweenDate(Date startDate, Date endDate, PageRequest pageRequest, String... status){
        return articleDAO.getArticleBetweenCreateDate(startDate,endDate,status,pageRequest);
    }

    @Override
    public Page<Article> findArticleBetweenModifiedDate(Date startDate, Date endDate, PageRequest pageRequest, String... status){
        return articleDAO.getArticleBetweenModifiedDate(startDate,endDate,status,pageRequest);
    }

    @Override
    public List<Tag> getArticleTags(Integer articleId){
        return tagDAO.getByArticleId(articleId);
    }

    @Override
    public Page<Article> findArticleByKeyword(String keyword, PageRequest pageRequest, String... status){
        //TODO
        return null;
    }
}
