package cn.lncsa.actions;

import cn.lncsa.common.RegexTools;
import cn.lncsa.data.model.User;
import com.opensymphony.xwork2.ActionSupport;
import org.apache.struts2.interceptor.RequestAware;
import org.apache.struts2.interceptor.SessionAware;

import java.util.Map;

/**
 * Created by catten on 16/4/25.
 */
public class UserAction extends ActionSupport implements SessionAware,RequestAware{
    private Map<String,Object> sessionContext;
    private Map<String,Object> requestContext;

    private String username;
    private String password;

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

    public String logout(){
        sessionContext.remove("passport");
        return "success";
    }
}
