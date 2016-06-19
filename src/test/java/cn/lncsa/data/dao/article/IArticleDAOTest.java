package cn.lncsa.data.dao.article;

import cn.lncsa.data.dao.user.IUserDAO;
import cn.lncsa.data.model.article.Article;
import cn.lncsa.data.model.user.User;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.ContextHierarchy;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import static org.junit.Assert.*;

/**
 * Created by catten on 16/6/12.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextHierarchy({
        //如果测试文件有依赖，则按照这个顺序加载，如果直接使用ContextConfiguration则只加载一个
        @ContextConfiguration("classpath:/cn/lncsa/applicationContext.xml")
})
//测试里事务默认回滚，但是建议加上
@Transactional
public class IArticleDAOTest {

    private IArticleDAO articleDAO;
    private IUserDAO userDAO;

    private User baseUser;
    private int articleCount = 50;

    @Before
    public void before(){
        baseUser = new User("userTest_a","");
        userDAO.save(baseUser);

        List<Article> articleList = new LinkedList<>();
        for(int i = 0; i < articleCount; i++){
            Article article = new Article();

            article.setTitle("Test article " + i);
            article.setCreateDate(new Date());
            article.setLastModifiedDate(article.getCreateDate());
            article.setPreviewSentences("just for test");
            article.setContext("#Hello!\n\n##Hello hello\n\n###" + i);
            article.setStatus("shown");
            article.setAuthor(baseUser);

            articleList.add(article);
        }
        //Create an empty user and an empty article
        Article tempA = new Article("","",new Date(65535),userDAO.save(new User()),"","");
        tempA.setLastModifiedDate(new Date(65589));
        articleList.add(tempA);
        articleDAO.save(articleList);
    }

    @Autowired
    public void setArticleDAO(IArticleDAO articleDAO) {
        this.articleDAO = articleDAO;
    }

    @Autowired
    public void setUserDAO(IUserDAO userDAO) {
        this.userDAO = userDAO;
    }

    @Test
    public void pagingArticles(){

        Pageable pageable = new PageRequest(0,5);
        Page<Article> page = articleDAO.findAll(pageable);

        assertFalse(page.getTotalPages() != 10);
        assertFalse(page.getTotalElements() != 50);
        assertFalse(page.getNumberOfElements() != 5);
        for (Article article : page.getContent()){
            assertNotNull(article);
        }
    }

    @Test
    public void findByTitleLike() throws Exception {
        Page<Article> articles = articleDAO.findByTitleLike("Test",new PageRequest(0,10));
        assertTrue(articles.getTotalElements() == articleCount);
        assertTrue(articles.getTotalPages() == articleCount / 10);
    }

    @Test
    public void findByAuthor() throws Exception {
        Page<Article> articles = articleDAO.findByAuthor(baseUser,new PageRequest(0,10));
        assertTrue(articles.getTotalElements() == articleCount);
        assertTrue(articles.getTotalPages() == articleCount / 10);
    }

    @Test
    public void findByAuthorId() throws Exception {
        Page<Article> articles = articleDAO.findByAuthorId(baseUser.getId(),new PageRequest(0,10));
        assertTrue(articles.getTotalElements() == articleCount);
        assertTrue(articles.getTotalPages() == articleCount / 10);
    }

    @Test
    public void getArticleBetweenCreateDate() throws Exception {
        Page<Article> articles = articleDAO.getArticleBetweenCreateDate(new Date(65534),new Date(65590),new PageRequest(0,10));
        assertTrue(articles.getTotalPages() == 1);
        assertTrue(articles.getTotalElements() == 1);
    }

    @Test
    public void getArticleBetweenModifiedDate() throws Exception {
        Page<Article> articles = articleDAO.getArticleBetweenModifiedDate(new Date(65534),new Date(65590),new PageRequest(0,10));
        assertTrue(articles.getTotalPages() == 1);
        assertTrue(articles.getTotalElements() == 1);
    }
}