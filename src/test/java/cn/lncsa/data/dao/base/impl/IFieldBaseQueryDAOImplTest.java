package cn.lncsa.data.dao.base.impl;

import cn.lncsa.data.dao.base.IFieldBaseQueryDAO;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.ContextHierarchy;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.junit.Assert.*;

/**
 * Created by catten on 10/13/16.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextHierarchy({
        @ContextConfiguration("classpath:applicationContext.xml")
})
public class IFieldBaseQueryDAOImplTest {

    @Qualifier("IFieldBaseQueryDAOImpl")
    @Autowired
    private IFieldBaseQueryDAO iFieldBaseQueryDAOImpl;

    @Test
    public void test(){


    }
}