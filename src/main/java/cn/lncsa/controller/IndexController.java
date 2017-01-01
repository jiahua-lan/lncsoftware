package cn.lncsa.controller;

import cn.lncsa.services.ArticleServices;
import cn.lncsa.services.BulletinServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by catten on 16/7/1.
 */
@Controller
public class IndexController {

    private BulletinServices bulletinServices;
    private ArticleServices articleServices;

    @Autowired
    private void setArticleServices(ArticleServices articleServices) {
        this.articleServices = articleServices;
    }

    @Autowired
    private void setBulletinServices(BulletinServices bulletinServices) {
        this.bulletinServices = bulletinServices;
    }

    @RequestMapping("/")
    public String index(Model model) {
        model.addAttribute("bulletin",bulletinServices.getLatestOne());
        model.addAttribute("articles",articleServices.getLatest(5));
        return "index";
    }

    @RequestMapping("/about")
    public String about(){
        return "about";
    }
}
