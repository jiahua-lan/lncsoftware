package cn.lncsa.data.dao;

import cn.lncsa.data.model.Bulletin;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.ContextHierarchy;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Date;
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
public class IBulletinDAOTest {

    private IBulletinDAO bulletinDAO;

    @Autowired
    public void setBulletinDAO(IBulletinDAO bulletinDAO) {
        this.bulletinDAO = bulletinDAO;
    }

    String[] types = new String[]{"app_guide","app_info","article","main_page","contact_info","friend_link"};
    @Before
    public void before(){
        for(String s : types){
            switch (s){
                case "contact_info":
                case "friend_link":
                {
                    List<Bulletin> bulletins = new ArrayList<>();
                    for (int i = 0; i < 10; i++){
                        Bulletin bulletin = new Bulletin();
                        bulletin.setDate(new Date());
                        bulletin.setContext("Test Contact Info " + i);
                        bulletin.setType(s);
                        bulletins.add(bulletin);
                    }
                    bulletinDAO.save(bulletins);
                }break;

                default:
                {
                    Bulletin bulletin = new Bulletin();
                    bulletin.setType(s);
                    bulletin.setDate(new Date());
                    bulletin.setContext("Test Bulletin");
                    bulletinDAO.save(bulletin);
                }break;
            }
        }


    }

    @Test
    public void getByType() throws Exception {
        for(String s : types){
            assertFalse(bulletinDAO.getByType(s).size() == 0);
        }
    }

    @Test
    public void getSpecialSignalBulletin(){
        assertNotNull(bulletinDAO.getMainPageBulletin());
        assertNotNull(bulletinDAO.getArticleBulletin());
        assertNotNull(bulletinDAO.getAppGuideBulletin());
        assertNotNull(bulletinDAO.getAppUsageBulletin());
    }

    @Test
    public void getMultiBulletin(){
        assertFalse(bulletinDAO.getContactInfoList().size() == 0);
        assertFalse(bulletinDAO.getFriendLinkList().size() == 0);
    }
}