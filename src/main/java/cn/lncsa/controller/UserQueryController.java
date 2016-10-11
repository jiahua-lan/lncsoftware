package cn.lncsa.controller;

import cn.lncsa.services.IPermissionServices;
import cn.lncsa.services.exceptions.UserOperateException;
import cn.lncsa.data.model.user.User;
import cn.lncsa.services.IUserServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.List;
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

    public Object getUserById(Integer userId){
        return null;
    }

    public Object getUserByName(String username){
        return null;
    }

    public Object searchUserByProfileInfo(Map<String,String> fields, Pageable pageable){
        return null;
    }
}

