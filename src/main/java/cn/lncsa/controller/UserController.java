package cn.lncsa.controller;

import cn.lncsa.data.model.User;
import cn.lncsa.services.UserServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpSession;

/**
 * Created by catten on 12/31/16.
 */
@Controller
@RequestMapping("/user")
public class UserController {

    private UserServices userServices;

    @Autowired
    private void setUserServices(UserServices userServices){
        this.userServices = userServices;
    }

    @RequestMapping(value = "/register",method = RequestMethod.GET)
    public String register(){
        return "register";
    }

    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public String register(@ModelAttribute User user, Model model){
        User result = userServices.save(user);
        model.addAttribute("reg_username",result.getName());
        return "registerSuccess";
    }

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String login(){
        return "login";
    }

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public String login(@ModelAttribute User user, Model model, HttpSession session){
        User key = userServices.getByName(user.getName());
        if(key == null || !key.getPassword().equals(user.getPassword())) return "loginFailed";
        model.addAttribute("login_username",key.getName());
        session.setAttribute("session_userid",key.getId());
        return "loginSuccess";
    }

    @RequestMapping(value = "/logout", method = RequestMethod.GET)
    public String logout(HttpSession session){
        session.removeAttribute("session_userid");
        return "logoutSuccess";
    }
}
