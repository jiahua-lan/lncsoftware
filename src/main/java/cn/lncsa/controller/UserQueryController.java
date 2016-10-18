package cn.lncsa.controller;

import cn.lncsa.common.ResultObject;
import cn.lncsa.data.dao.user.IUserProfileDAO;
import cn.lncsa.data.model.user.UserProfile;
import cn.lncsa.services.IPermissionServices;
import cn.lncsa.services.exceptions.UserOperateException;
import cn.lncsa.data.model.user.User;
import cn.lncsa.services.IUserServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

/**
 * Created by cattenlinger on 16/9/20.
 */
@Controller
@RequestMapping("/users/query")
public class UserQueryController {

    private IUserServices userServices;
    private IUserProfileDAO userProfileDAO;

    @Autowired
    public void setUserServices(IUserServices userServices) {
        this.userServices = userServices;
    }

    @Autowired
    public void setUserProfileDAO(@Qualifier("IUserProfileDAO") IUserProfileDAO userProfileDAO) {
        this.userProfileDAO = userProfileDAO;
    }

    // /users/query/{userId}
    public Object getUserById(Integer userId){
        User user = userServices.get(userId);
        return user == null ? new ResultObject(false,"not found") : new ResultObject(true,user);
    }

    // /users/query/{username}
    public Object getUserByName(String username){
        User user = userServices.getByName(username);
        return user == null ? new ResultObject(false,"not found") : new ResultObject(true,user);
    }

    // /users/query
    // fieldName = {object}
    // ...
    public Object searchUserByProfileInfo(Map<String,Object> fields, Pageable pageable){
        return null;
    }
}

