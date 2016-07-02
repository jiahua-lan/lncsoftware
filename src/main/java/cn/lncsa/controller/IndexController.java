package cn.lncsa.controller;

import cn.lncsa.services.IArticleServices;
import cn.lncsa.services.IBulletinServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Created by catten on 16/7/1.
 */
@Controller
public class IndexController {

    private IArticleServices articleServices;
    private IBulletinServices bulletinServices;

    @Autowired
    public void setArticleServices(IArticleServices articleServices) {
        this.articleServices = articleServices;
    }

    @Autowired
    public void setBulletinServices(IBulletinServices bulletinServices) {
        this.bulletinServices = bulletinServices;
    }

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String index(Model model){
        model.addAttribute("recentArticle",articleServices.getLatestArticle());
        model.addAttribute("titleBulletin",bulletinServices.getIndexTitleBulletin());
        model.addAttribute("topBoardBulletin",bulletinServices.getIndexBoardBulletin());
        return "WEB-INF/views/index";
    }
}
