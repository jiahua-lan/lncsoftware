package cn.lncsa.controller;

import cn.lncsa.data.model.User;
import cn.lncsa.services.UserServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

/**
 * Created by catten on 12/31/16.
 */
@Controller
@RequestMapping("/user")
public class UserController {

    private UserServices userServices;

    @Autowired
    private void setUserServices(UserServices userServices) {
        this.userServices = userServices;
    }

    @RequestMapping(value = "/register", method = RequestMethod.GET)
    public String register() {
        return "register";
    }

    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public String register(@ModelAttribute @Valid User user, BindingResult result, Model model) {
        if(result.hasErrors()) return "dialogs/registerFailed";

        User theUser = userServices.getByName(user.getName());
        if (theUser != null) return "dialogs/registerFailed";

        theUser = userServices.save(user);
        model.addAttribute("reg_username", theUser.getName());
        return "dialogs/registerSuccess";
    }

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String login() {
        return "login";
    }

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public String login(@ModelAttribute @Valid User userForm, BindingResult result, Model model, HttpSession session) {

        User user = userServices.getByName(userForm.getName());
        if(result.hasErrors()) return "dialogs/loginFailed";
        if (user == null || !user.getPassword().equals(userForm.getPassword())) return "dialogs/loginFailed";

        model.addAttribute("login_username", user.getName());

        session.setAttribute("session_userid", user.getId());
        session.setAttribute("session_username",user.getName());
        return "loginSuccess";
    }

    @RequestMapping(value = "/logout", method = RequestMethod.GET)
    public String logout(HttpSession session) {
        session.removeAttribute("session_userid");
        return "dialogs/logoutSuccess";
    }
}
