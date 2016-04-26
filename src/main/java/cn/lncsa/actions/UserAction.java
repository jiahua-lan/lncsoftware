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

    @Override
    public void setRequest(Map<String, Object> map) {
        sessionContext = map;
    }

    @Override
    public void setSession(Map<String, Object> map) {
        requestContext = map;
    }

    public String login(){
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
                                return "banned";
                        }
                    }
                }
            }
        }
        requestContext.put("loginResult","failed");
        return "failed";
    }
}
