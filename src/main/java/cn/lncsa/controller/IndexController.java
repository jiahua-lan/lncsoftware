package cn.lncsa.controller;

import cn.lncsa.services.ArticleServices;
import cn.lncsa.services.BulletinServices;
import cn.lncsa.services.TopicServices;
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
    private TopicServices topicServices;

    @Autowired
    private void setArticleServices(ArticleServices articleServices) {
        this.articleServices = articleServices;
    }

    @Autowired
    private void setBulletinServices(BulletinServices bulletinServices) {
        this.bulletinServices = bulletinServices;
    }

    @Autowired
    private void setTopicServices(TopicServices topicServices){
        this.topicServices = topicServices;
    }

    @RequestMapping("/")
    public String index(Model model) {
        model.addAttribute("bulletins",bulletinServices.getAvailableItems(5));
        model.addAttribute("articles",articleServices.getLatest(5));
        model.addAttribute("topics",topicServices.mostWeightTopics(10));
        return "index";
    }

    @RequestMapping("/about")
    public String about(){
        return "about";
    }
}
