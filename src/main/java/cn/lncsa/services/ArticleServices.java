package cn.lncsa.services;

import cn.lncsa.data.model.Article;
import cn.lncsa.data.model.ArticleBody;
import cn.lncsa.data.model.Topic;
import cn.lncsa.data.repository.IArticleBodyDAO;
import cn.lncsa.data.repository.IArticleDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * Created by catten on 12/31/16.
 */
@Service
public class ArticleServices{

    private IArticleDAO articleDAO;
    private IArticleBodyDAO articleBodyDAO;

    @Autowired
    private void setArticleDAO(IArticleDAO articleDAO){
        this.articleDAO = articleDAO;
    }

    @Autowired
    private void setArticleBodyDAO(IArticleBodyDAO articleBodyDAO) {
        this.articleBodyDAO = articleBodyDAO;
    }

    public void save(Article article, ArticleBody body) {
        if(article.getId() == null) article.setCreateDate(new Date());
        //body.setLatestModifiedDate(new Date());

        articleBodyDAO.saveAndFlush(body);
        article.setBody(body);

        articleDAO.save(article);
    }

    
    public void saveHead(Article article) {

    }

    
    public void saveBody(ArticleBody articleBody) {

    }

    
    public void delete(Integer articleId) {

    }

    
    public Article get(Integer articleId) {
        return articleDAO.findOne(articleId);
    }

    
    public Page<Article> get(Pageable pageable, String... status) {
        return articleDAO.findAll(status,pageable);
    }

    
    public List<Article> getLatest(Integer count) {
        return articleDAO.findRecent(Article.STATUS_PUBLISHED, new PageRequest(0,count)).getContent();
    }

    
    public Page<Article> getByTag(Topic topic, Pageable pageable, String... status) {
        return null;
    }

    
    public Page<Article> getByUserId(Integer userId, Pageable pageable, String... status) {
        return null;
    }

    
    public Page<Article> findBetweenDate(Date startDate, Date endDate, Pageable pageable, String... status) {
        return null;
    }

    
    public Page<Article> findBetweenModifiedDate(Date startDate, Date endDate, Pageable pageable, String... status) {
        return null;
    }

    
    public Page<Article> findByKeyword(String keyword, Pageable pageable, String... status) {
        return null;
    }

    
    public ArticleBody getBody(Integer articleId) {
        return null;
    }
}
