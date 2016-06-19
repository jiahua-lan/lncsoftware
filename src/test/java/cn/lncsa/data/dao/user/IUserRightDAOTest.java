package cn.lncsa.data.dao.user;

import cn.lncsa.data.dao.user.IRightDAO;
import cn.lncsa.data.dao.user.IUserDAO;
import cn.lncsa.data.dao.user.IUserRightDAO;
import cn.lncsa.data.model.user.Right;
import cn.lncsa.data.model.user.User;
import cn.lncsa.data.model.user.UserRight;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
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

    @Before
    public void setUp() throws Exception {
        String[] rights = new String[]{"update", "delete", "insert", "query"};

        //Initial rights
        List<Right> rightList = new LinkedList<>();
        for (String s : rights) rightList.add(new Right(s));
        rightDAO.save(rightList);

        //Initial user-right rules
        List<UserRight> userRights = new LinkedList<>();
        for(int i = 0; i < 20; i++){
            User user = new User("testUser_ur" + i,"");
            userDAO.save(user);
            if(i < 10){
                userRights.add(new UserRight(user,rightList.get(0)));
                userRights.add(new UserRight(user,rightList.get(1)));
            }else{
                userRights.add(new UserRight(user,rightList.get(2)));
                userRights.add(new UserRight(user,rightList.get(3)));
            }
        }
        userRightDAO.save(userRights);
    }

    @Test
    public void getUserByRight() throws Exception {
        List<Right> rights = rightDAO.findAll();
        for(Right right : rights){
            Page<User> page = userRightDAO.getUserByRight(right,new PageRequest(0,5));
            assertTrue(page.getTotalElements() == 10);
        }
    }

    @Test
    public void getUserByRightId() throws Exception {
        List<Right> rights = rightDAO.findAll();
        for(Right right : rights){
            Page<User> page = userRightDAO.getUserByRightId(right.getId(),new PageRequest(0,5));
            assertTrue(page.getTotalElements() == 10);
        }
    }

    @Test
    public void getRightByUser() throws Exception {
        Iterable<User> users = userDAO.findAll();
        for (User user : users){
            List<Right> rights = userRightDAO.getRightByUser(user);
            assertTrue(rights.size() == 2);
        }
    }

    @Test
    public void getRightByUserId() throws Exception {
        Iterable<User> users = userDAO.findAll();
        for (User user : users){
            List<Right> rights = userRightDAO.getRightByUserId(user.getId());
            assertTrue(rights.size() == 2);
        }
    }

    @Test
    public void queryRightRelationByUser() throws Exception {
        //TODO
    }

    @Test
    public void queryRightRelationByUserId() throws Exception {
        //TODO
    }
}