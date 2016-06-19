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

    @Before
    public void before(){
        User user = new User("userTest_a","");
        userDAO.save(user);

        List<Article> articleList = new LinkedList<>();
        for(int i = 0; i < 50; i++){
            Article article = new Article();

            article.setTitle("Test article " + i);
            article.setCreateDate(new Date());
            article.setLastModifiedDate(article.getCreateDate());
            article.setPreviewSentences("just for test");
            article.setContext("#Hello!\n\n##Hello hello\n\n###" + i);
            article.setStatus("shown");
            article.setAuthor(user);

            articleList.add(article);
        }
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
        //TODO
    }

    @Test
    public void findByAuthor() throws Exception {
        //TODO
    }

    @Test
    public void findByAuthorId() throws Exception {
        //TODO

    }

    @Test
    public void getArticleBetweenCreateDate() throws Exception {

    }

    @Test
    public void getArticleBetweenModifiedDate() throws Exception {

    }
}