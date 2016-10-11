package cn.lncsa.controller;

import cn.lncsa.services.IPermissionServices;
import cn.lncsa.services.exceptions.UserOperateException;
import cn.lncsa.data.model.user.User;
import cn.lncsa.services.IUserServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by cattenlinger on 16/9/20.
 */
@Controller
@RequestMapping("/users/query")
public class UserQueryController {

    private IUserServices userServices;

    @Autowired
    public void setUserServices(IUserServices userServices) {
        this.userServices = userServices;
    }

    public Object queryUserById(Integer userId){
        return null;
    }

    public Object queryUserByName(String username){
        return null;
    }

    public Object queryUserByNickname(String nickname){
        return null;
    }

    public Object queryUserByContactInfo(String contactInfo){
        return null;
    }
}

