package cn.lncsa.controller;

import cn.lncsa.data.model.user.User;
import cn.lncsa.services.IUserServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

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
}
