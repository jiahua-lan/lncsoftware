package cn.lncsa.actions;

import cn.lncsa.common.RegexTools;
import cn.lncsa.data.factory.UserDAO;
import cn.lncsa.data.model.User;
import com.opensymphony.xwork2.ActionSupport;
import org.apache.struts2.interceptor.RequestAware;
import org.apache.struts2.interceptor.SessionAware;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Map;

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
            user = User.getDao().getUserByName(username);
            if(user != null){
                if(user.getPassword().equals(password)){
                    for (String s : user.getRights()){
                        switch (s){
                            case "login":
                                sessionContext.put("passport",user);
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
        User user = User.getDao().getUserByName(username);
        if(user != null){
            requestContext.put("error","duplicate");
            return "form";
        }else user = new User();
        user.setName(username);
        user.setPassword(password);
        user.setContactInfo(contactInfo);
        ArrayList<String> rights = new ArrayList<>(1);
        rights.add("login");
        user.setRights(rights);
        User.getDao().create(user);
        return "success";
    }

    public String updateContact(){
        if(sessionContext.get("passport")==null) return "login";
        if(contactInfo != null && RegexTools.legalContactInfo(contactInfo)){
            User user = (User) sessionContext.get("passport");
            user.setContactInfo(contactInfo);
            User.getDao().update(user);
            sessionContext.put("passport",User.getDao().read(user.getObjectId()));
        }
        return "success";
    }

    public String updatePassword(){
        User user = (User) sessionContext.get("passport");
        if(user == null) return "login";
        if(password != null){
            if(RegexTools.legalPassword(password) && password.equals(confirmedPassword)){
                user.setPassword(password);
                User.getDao().update(user);
                sessionContext.put("passport",User.getDao().read(user.getObjectId()));
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
