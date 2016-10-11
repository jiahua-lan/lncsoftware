package cn.lncsa.controller;

import cn.lncsa.data.model.user.User;
import cn.lncsa.data.model.user.UserProfile;
import cn.lncsa.services.IUserServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Objects;

/**
 * Created by cattenlinger on 2016/10/6.
 */
@Controller
@RequestMapping("/user/manage")
public class UserManagementController {

    private IUserServices userServices;

    @Autowired
    public void setUserServices(IUserServices userServices) {
        this.userServices = userServices;
    }

    public Object registUser(User user) {
        return null;
    }

    public Object updateUserInfo(UserProfile userProfile) {
        return null;
    }

    public Object updateUser(User user){
        return null;
    }
}
