package cn.lncsa.controller;

import cn.lncsa.data.model.article.Article;
import cn.lncsa.services.IArticleServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * Created by catten on 16/7/3.
 */
@Controller
@RequestMapping("/article")
public class ArticleController {
    private IArticleServices articleServices;

    @Autowired
    public void setArticleServices(IArticleServices articleServices) {
        this.articleServices = articleServices;
    }

    @RequestMapping(value = "/list/latest", method = RequestMethod.GET)
    public @ResponseBody List<Article> latestArticleList(){
        return articleServices.getLatestArticle();
    }
}
