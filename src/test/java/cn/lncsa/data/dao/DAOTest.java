package cn.lncsa.data.dao;

import cn.lncsa.data.dao.article.*;
import cn.lncsa.data.dao.user.IRightDAO;
import cn.lncsa.data.dao.user.IUserDAO;
import cn.lncsa.data.dao.user.IUserRightDAO;
import cn.lncsa.data.model.*;
import cn.lncsa.data.model.article.*;
import cn.lncsa.data.model.user.Right;
import cn.lncsa.data.model.user.User;
import cn.lncsa.data.model.user.UserRight;
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
    private IArticleCommitDAO articleCommitDAO;
    private IArticleTagDAO articleTagDAO;
    private IBulletinDAO bulletinDAO;
    private ICommitDAO commitDAO;
    private IRightDAO rightDAO;
    private ITagDAO tagDAO;
    private IUserDAO userDAO;
    private IUserRightDAO userRightDAO;

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

    @Autowired
    public void setArticleCommitDAO(IArticleCommitDAO articleCommitDAO) {
        this.articleCommitDAO = articleCommitDAO;
    }

    @Autowired
    public void setArticleTagDAO(IArticleTagDAO articleTagDAO) {
        this.articleTagDAO = articleTagDAO;
    }

    @Autowired
    public void setRightDAO(IRightDAO rightDAO) {
        this.rightDAO = rightDAO;
    }

    @Autowired
    public void setTagDAO(ITagDAO tagDAO) {
        this.tagDAO = tagDAO;
    }

    @Autowired
    public void setUserRightDAO(IUserRightDAO userRightDAO) {
        this.userRightDAO = userRightDAO;
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
    public void testArticleCommitDAO(){
        ArticleCommit articleCommit = new ArticleCommit();
        articleCommitDAO.save(articleCommit);
        assertNotNull(articleCommit.getId());
    }

    @Test
    public void testArticleTagDAO(){
        ArticleTag articleTag = new ArticleTag();
        articleTagDAO.save(articleTag);
        assertNotNull(articleTag.getId());
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
    public void testRightDAO(){
        Right right = new Right();
        rightDAO.save(right);
        assertNotNull(right.getId());
    }

    @Test
    public void testTagDAO(){
        Tag tag = new Tag();
        tagDAO.save(tag);
        assertNotNull(tag.getId());
    }

    @Test
    public void testUserRightDAO(){
        UserRight userRight = new UserRight();
        userRightDAO.save(userRight);
        assertNotNull(userRight.getId());
    }

    @Test
    public void testUserDAO(){
        User user = new User();
        userDAO.save(user);
        assertNotNull(user.getId());
    }
}