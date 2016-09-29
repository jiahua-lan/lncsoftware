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
@RequestMapping("/users")
public class UserController {

    private IUserServices userServices;
    private IPermissionServices permissionServices;

    @Autowired
    public void setUserServices(IUserServices userServices) {
        this.userServices = userServices;
    }

    /**
     * Query user by userId
     *
     * @param userId
     * @return
     */
    @RequestMapping(value = "/id/{userId}", method = RequestMethod.GET)
    public
    @ResponseBody
    Map<String, Object> queryUserById(@PathVariable("userId") Integer userId) {
        try {
            return filterUserInfo(userServices.get(userId));
        } catch (UserOperateException e) {
            return null;
        }
    }

    /**
     * Query user's rights by userId
     *
     * @param userId
     * @return
     */
    @RequestMapping(value = "/id/{userId}/rights", method = RequestMethod.GET)
    public
    @ResponseBody
    Map<String, Object> queryUserRights(@PathVariable("userId") Integer userId) {
        HashMap<String, Object> hashMap = new HashMap<>();
        hashMap.put("userId", userId);
        hashMap.put("rights", permissionServices.queryUserRoles(userId));
        return hashMap;
    }

    /**
     * Query user by username
     *
     * @param username
     * @return
     */
    @RequestMapping(value = "/{username}", method = RequestMethod.GET)
    public
    @ResponseBody
    Map<String, Object> queryUserByName(@PathVariable("username") String username) {
        try {
            return filterUserInfo(userServices.getUserByName(username));
        } catch (UserOperateException e) {
            return null;
        }
    }

    /**
     * Query user's rights by username
     *
     * @param username
     * @return
     */
    @RequestMapping(value = "/{username}/rights", method = RequestMethod.GET)
    public
    @ResponseBody
    Map<String, Object> queryUserRights(@PathVariable("username") String username) {
        try {
            HashMap<String, Object> hashMap = new HashMap<>();
            hashMap.put("username", username);
            hashMap.put("rights", permissionServices.queryUserRoles(userServices.getUserByName(username).getId()));
            return hashMap;
        } catch (UserOperateException e) {
            return null;
        }
    }

    /**
     * Login to website
     * <p>
     * If success, the method put an User to "session_passport" in session
     *
     * @param username username
     * @param password password
     * @param session  current session
     * @return
     */
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public
    @ResponseBody
    Map<String, Object> login(
            @RequestParam("username") String username,
            @RequestParam("password") String password,
            HttpSession session) {
        try {
            User user = userServices.getUserByName(username);
            HashMap<String, Object> hashMap = new HashMap<>();
            if (user.getPassword().equals(password)) {
                if (session.getAttribute("session_passport") != null) {
                    session.removeAttribute("session_passport");
                }
                session.setAttribute("session_passport", user);
                hashMap.put("success", true);
            } else {
                hashMap.put("success", false);
            }
            hashMap.put("userId", user.getId());
            return hashMap;
        } catch (UserOperateException e) {
            return null;
        }
    }

    /**
     * Just clear "session_passport" attribute in session
     *
     * @param session current session
     * @return
     */
    @RequestMapping(value = "/logout", method = RequestMethod.GET)
    public
    @ResponseBody
    Map<String, Object> logout(HttpSession session) {
        session.removeAttribute("session_passport");
        Map<String, Object> resultMap = new HashMap<>();
        resultMap.put("success", true);
        return resultMap;
    }


    /*
    *   private producer
    */

    //Filter user info
    private Map<String, Object> filterUserInfo(User user) {
        Map<String, Object> userInfo = new HashMap<>();
        userInfo.put("id", user.getId());
        userInfo.put("name", user.getName());
        userInfo.put("nickName", user.getNickName());
        userInfo.put("contact", user.getContactInfo());
        return userInfo;
    }
}

