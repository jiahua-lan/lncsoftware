package cn.lncsa.controller;

import cn.lncsa.common.ResultObject;
import cn.lncsa.services.IArticleServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by cattenlinger on 2016/10/6.
 */
@Controller
@RequestMapping("articles/view")
public class ArticleShowController {
    private IArticleServices articleServices;

    @Autowired
    public void setArticleServices(IArticleServices articleServices) {
        this.articleServices = articleServices;
    }

    /*
    * Methods
    *
    * */

    // /article/view/{articleId}
    public Object showArticle(int articleId){
        return new ResultObject(true,articleServices.getBody(articleId));
    }
}
