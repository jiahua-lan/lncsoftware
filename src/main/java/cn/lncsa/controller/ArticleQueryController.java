package cn.lncsa.controller;

import cn.lncsa.common.ResultObject;
import cn.lncsa.services.ITaggingServices;
import cn.lncsa.services.exceptions.UserOperateException;
import cn.lncsa.data.dto.IArticlePreview;
import cn.lncsa.data.dto.impl.ArticlePreview;
import cn.lncsa.data.model.article.Article;
import cn.lncsa.services.IArticleServices;
import cn.lncsa.services.IUserServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.*;

/**
 * Created by catten on 16/7/3.
 */
@Controller
@RequestMapping("/articles/query")
public class ArticleQueryController {
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

    /*
    * Methods
    *
    * */

    public Object queryLatestArticles(int count,String status){
        List<Article> articles = articleServices.getLatest(count,status);
        return new ResultObject(true,articles);
    }

    public Object queryArticles(Pageable pageable,String... status){
        return new ResultObject(true,articleServices.get(pageable,status));
    }

    public Object queryArticlesUnderTag(Integer tagId,Pageable pageable,List<String> status){
        return new ResultObject(true,taggingServices.queryArticlesUnderTag(tagId,status,pageable));
    }

    public Object queryArticlesByUser(int userId,Pageable pageable,String... status){
        return new ResultObject(true,articleServices.getByUserId(userId,pageable,status));
    }
}
