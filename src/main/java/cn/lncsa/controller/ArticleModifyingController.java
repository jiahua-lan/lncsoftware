package cn.lncsa.controller;

import cn.lncsa.data.model.article.Article;
import cn.lncsa.services.IArticleServices;
import cn.lncsa.services.ITaggingServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by cattenlinger on 2016/10/6.
 */
@Controller
@RequestMapping("/articles/modify")
public class ArticleModifyingController {

    private IArticleServices articleServices;
    private ITaggingServices taggingServices;

    @Autowired
    public void setArticleServices(IArticleServices articleServices) {
        this.articleServices = articleServices;
    }

    @Autowired
    public void setTaggingServices(ITaggingServices taggingServices) {
        this.taggingServices = taggingServices;
    }

    public Object saveArticle(Article article){
        return null;
    }

    public Object deleteArticle(Integer articleId){
        return null;
    }

    public Object setArticleStatus(String status){
        return null;
    }
}
