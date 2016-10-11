package cn.lncsa.data.model.user;

import com.alibaba.fastjson.JSON;
import org.junit.Test;

import java.util.Date;

import static org.junit.Assert.*;

/**
 * Created by catten on 10/11/16.
 */
public class UserTest {
    @Test
    public void testUserPasswordField(){
        User user = new User("User","123456");
        user.setId(123);
        user.setProfileId(0);
        user.setRegisterDate(new Date());
        String jsonString = JSON.toJSONString(user);
        System.out.println(jsonString);
        assertFalse(jsonString.contains("password"));
    }

}