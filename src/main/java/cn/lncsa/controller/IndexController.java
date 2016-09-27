package cn.lncsa.controller;

import cn.lncsa.data.model.Bulletin;
import cn.lncsa.data.model.user.User;
import cn.lncsa.services.IArticleServices;
import cn.lncsa.services.IBulletinServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpSession;
import java.util.Map;
import java.util.Properties;

/**
 * Created by catten on 16/7/1.
 */
@Controller
public class IndexController {

    private IBulletinServices bulletinServices;

    @Autowired
    public void setBulletinServices(IBulletinServices bulletinServices) {
        this.bulletinServices = bulletinServices;
    }

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String index(Model model, HttpSession session){
        model.addAttribute(bulletinServices.getBulletin("main_page"));
        Object object = session.getAttribute("session_passport");
        if(object instanceof User){
            model.addAttribute("nickName",((User) object).getNickName());
        }
        return "index";
    }
}
