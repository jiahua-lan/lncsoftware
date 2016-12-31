package cn.lncsa.controller;

import cn.lncsa.data.model.Article;
import cn.lncsa.data.model.ArticleBody;
import cn.lncsa.services.ArticleServices;
import cn.lncsa.services.UserServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;

/**
 * Created by catten on 12/31/16.
 */
@Controller
@RequestMapping("/article")
public class ArticleController {

    private ArticleServices articleServices;
    private UserServices userServices;

    @Autowired
    private void setArticleServices(ArticleServices articleServices) {
        this.articleServices = articleServices;
    }

    @Autowired
    private void setUserServices(UserServices userServices){
        this.userServices = userServices;
    }

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String articles(@RequestParam(value = "page",defaultValue = "0") int page, Model model){
        model.addAttribute("articles",articleServices.get(new PageRequest(page,10), Article.STATUS_PUBLISHED));
        return "articles";
    }

    @RequestMapping(value = "/write",method = RequestMethod.GET)
    public String write(){
        return "writeArticle";
    }

    @RequestMapping(value = "/write",method = RequestMethod.POST)
    public String write(@ModelAttribute Article article, @RequestParam("article_body") String body, Model model, HttpSession session){
        Integer userid = (Integer) session.getAttribute("session_userid");
        if(userid == null) return "redirect:/user/login";

        article.setAuthor(userServices.get(userid));

        articleServices.save(article,new ArticleBody(body));
        model.addAttribute("article_title",article.getTitle());

        return "articlePosted";
    }

}
