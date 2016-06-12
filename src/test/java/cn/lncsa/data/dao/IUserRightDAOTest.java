package cn.lncsa.data.dao;

import cn.lncsa.data.model.Right;
import cn.lncsa.data.model.User;
import cn.lncsa.data.model.UserRight;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.ContextHierarchy;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

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
public class IUserRightDAOTest {
    private IUserDAO userDAO;
    private IRightDAO rightDAO;
    private IUserRightDAO userRightDAO;

    @Autowired
    public void setUserDAO(IUserDAO userDAO) {
        this.userDAO = userDAO;
    }

    @Autowired
    public void setRightDAO(IRightDAO rightDAO) {
        this.rightDAO = rightDAO;
    }

    @Autowired
    public void setUserRightDAO(IUserRightDAO userRightDAO) {
        this.userRightDAO = userRightDAO;
    }


    User user;
    @Before
    public void setUp() throws Exception {
        String[] rights = new String[]{"update", "delete", "insert", "query"};
        user = new User();
        user.setName("test user");
        userDAO.save(user);

        List<UserRight> userRights = new LinkedList<>();
        for (String s : rights){
            Right right = new Right();
            right.setName(s);
            rightDAO.save(right);

            UserRight userRight = new UserRight();
            userRight.setUser(user);
            userRight.setRight(right);
            userRights.add(userRight);
        }
        userRightDAO.save(userRights);
    }

    @Test
    public void getUserRightsByUser(){
        assertTrue(userRightDAO.getByUser(user).size() == 4);
    }

    @Test
    public void getUserRightsById(){
        assertTrue(userRightDAO.getByUserId(user.getId()).size() == 4);
    }

    @Test
    public void getRightUsersByRight(){
        List<Right> rights = rightDAO.findAll();
        for(Right right : rights){
            User user1 = userRightDAO.getByRight(right).get(0).getUser();
            assertTrue(user1.getId().intValue() == user.getId().intValue());
        }
    }

    @Test
    public void getRightUserByRightId(){
        List<Right> rights = rightDAO.findAll();
        for(Right right : rights){
            User user1 = userRightDAO.getByRightId(right.getId()).get(0).getUser();
            assertTrue(user1.getId().intValue() == user.getId().intValue());
        }
    }

}