package cn.lncsa.data.dao;

import cn.lncsa.data.model.*;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.ContextHierarchy;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.Assert.*;

/**
 * Created by catte on 2016/6/12.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextHierarchy({
        //如果测试文件有依赖，则按照这个顺序加载，如果直接使用ContextConfiguration则只加载一个
        @ContextConfiguration("classpath:/cn/lncsa/applicationContext.xml")
})
//测试里事务默认回滚，但是建议加上
@Transactional
public class DAOTest {
    private IAppInfoDAO appInfoDAO;
    private IArticleDAO articleDAO;
    private IBulletinDAO bulletinDAO;
    private ICommitDAO commitDAO;
    private IUserDAO userDAO;

    @Autowired
    public void setAppInfoDAO(IAppInfoDAO appInfoDAO) {
        this.appInfoDAO = appInfoDAO;
    }

    @Autowired
    public void setArticleDAO(IArticleDAO articleDAO) {
        this.articleDAO = articleDAO;
    }

    @Autowired
    public void setBulletinDAO(IBulletinDAO bulletinDAO) {
        this.bulletinDAO = bulletinDAO;
    }

    @Autowired
    public void setCommitDAO(ICommitDAO commitDAO) {
        this.commitDAO = commitDAO;
    }

    @Autowired
    public void setUserDAO(IUserDAO userDAO) {
        this.userDAO = userDAO;
    }

    @Test
    public void testAppInfoDAO(){
        AppInfo appInfo = new AppInfo();
        appInfoDAO.save(appInfo);
        assertNotNull(appInfo.getId());
    }

    @Test
    public void testArticleDAO(){
        Article article = new Article();
        articleDAO.save(article);
        assertNotNull(article.getId());
    }

    @Test
    public void testBulletinDAO(){
        Bulletin bulletin = new Bulletin();
        bulletinDAO.save(bulletin);
        assertNotNull(bulletin.getId());
    }

    @Test
    public void testCommitDAO(){
        Commit commit = new Commit();
        commitDAO.save(commit);
        assertNotNull(commit.getId());
    }

    @Test
    public void testUserDAO(){
        User user = new User();
        userDAO.save(user);
        assertNotNull(user.getId());
    }
}