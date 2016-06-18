package cn.lncsa.actions;

import cn.lncsa.common.RegexTools;
import cn.lncsa.data.Passport;
import cn.lncsa.data.dao.IRightDAO;
import cn.lncsa.data.dao.IUserDAO;
import cn.lncsa.data.dao.IUserRightDAO;
import cn.lncsa.data.model.Right;
import cn.lncsa.data.model.User;
import cn.lncsa.data.model.UserRight;
import com.opensymphony.xwork2.ActionSupport;
import org.apache.struts2.interceptor.RequestAware;
import org.apache.struts2.interceptor.SessionAware;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.*;

/**
 * Created by catten on 16/4/25.
 */
public class UserAction extends ActionSupport implements SessionAware,RequestAware{
    private Map<String,Object> sessionContext;
    private Map<String,Object> requestContext;

    private String username;
    private String password;
    private String confirmedPassword;
    private String contactInfo;

    private IUserDAO userDAO;
    private IUserRightDAO userRightDAO;
    private IRightDAO rightDAO;

    @Autowired
    public void setUserDAO(IUserDAO userDAO) {
        this.userDAO = userDAO;
    }

    @Autowired
    public void setUserRightDAO(IUserRightDAO userRightDAO) {
        this.userRightDAO = userRightDAO;
    }

    @Autowired
    public void setRightDAO(IRightDAO rightDAO) {
        this.rightDAO = rightDAO;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public void setRequest(Map<String, Object> map) {
        requestContext = map;
    }

    @Override
    public void setSession(Map<String, Object> map) {
        sessionContext = map;
    }

    public String login(){
        if(sessionContext.get("passport") != null) return "success";
        if(username == null && password == null) return "login";
        User user;
        if(username != null && RegexTools.legalUsername(username)){
            user = userDAO.getByName(username);
            if(user != null){
                if(user.getPassword().equals(password)){
                    List<UserRight> userRights = userRightDAO.getByUser(user);
                    List<Right> rights = new LinkedList<>();
                    for (UserRight userRight : userRights) rights.add(userRight.getRight());
                    for (UserRight userRight : userRights){
                        switch (userRight.getRight().getName()){
                            case "login":
                                sessionContext.put("passport",new Passport(user,rights));
                                return "success";

                            case "banned":
                                requestContext.put("loginResult","banned");
                                return "failed";
                        }
                    }
                }
            }
        }
        requestContext.put("loginResult","failed");
        return "failed";
    }

    public String self(){
        User user = (User) sessionContext.get("passport");
        if(user == null){
            return "login";
        }
        requestContext.put("userInfo",user);
        return "success";
    }

    public String registry(){
        if (username == null || password == null || contactInfo == null) {
            return "form";
        }
        if (!RegexTools.legalUsername(username) || !RegexTools.legalPassword(password) || !RegexTools.legalContactInfo(contactInfo)){
            requestContext.put("error","field");
            return "form";
        }
        if(!password.equals(confirmedPassword)){
            requestContext.put("error","field");
            return "form";
        }
        User user = userDAO.getByName(username);
        if(user != null){
            requestContext.put("error","duplicate");
            return "form";
        }else user = new User();
        user.setName(username);
        user.setPassword(password);
        user.setContactInfo(contactInfo);
        Right right = rightDAO.getByName("login");
        if(right == null){
            requestContext.put("error","system error : no right called \"login\"");
            return "failed";
        }
        UserRight userRight = new UserRight();
        userRight.setRight(right);
        userRight.setUser(user);
        userDAO.save(user);
        userRightDAO.save(userRight);
        return "success";
    }

    public String updateContact(){
        if(sessionContext.get("passport")==null) return "login";
        if(contactInfo != null && RegexTools.legalContactInfo(contactInfo)){
            Passport passport = (Passport) sessionContext.get("passport");
            User user = passport.getUser();
            user.setContactInfo(contactInfo);
            userDAO.save(user);
        }
        return "success";
    }

    public String updatePassword(){
        User user = (User) sessionContext.get("passport");
        if(user == null) return "login";
        if(password != null){
            if(RegexTools.legalPassword(password) && password.equals(confirmedPassword)){
                user.setPassword(password);
                userDAO.save(user);
            }
        }
        return "success";
    }

    public String logout(){
        sessionContext.remove("passport");
        return "success";
    }

    public String getContactInfo() {
        return contactInfo;
    }

    public void setContactInfo(String contactInfo) {
        this.contactInfo = contactInfo;
    }

    public String getConfirmedPassword() {
        return confirmedPassword;
    }

    public void setConfirmedPassword(String confirmedPassword) {
        this.confirmedPassword = confirmedPassword;
    }
}
