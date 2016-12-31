package cn.lncsa.controller;

import cn.lncsa.services.IBulletinServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpSession;

/**
 * Created by catten on 16/7/1.
 */
@Controller
public class IndexController {

//    @RequestMapping(value = "/", method = RequestMethod.GET)
//    public String index(Model model, HttpSession session){
//        return "index";
//    }

    @RequestMapping("/")
    public String index(){
        return "index";
    }
}
