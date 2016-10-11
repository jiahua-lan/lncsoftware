package cn.lncsa.controller;

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
    private IUserServices userServices;
    private ITaggingServices taggingServices;

    @Autowired
    public void setArticleServices(IArticleServices articleServices) {
        this.articleServices = articleServices;
    }

    @Autowired
    public void setUserServices(IUserServices userServices) {
        this.userServices = userServices;
    }

    @Autowired
    public void setTaggingServices(ITaggingServices taggingServices) {
        this.taggingServices = taggingServices;
    }

    public Object queryLatestArticles(int count,boolean preview){
        return null;
    }

    public Object queryArticles(String ordering,boolean preview,Pageable pageable,String... status){
        return null;
    }

    public Object queryArticlesUnderTag(String tagTitle,boolean preview,String ordering,Pageable pageable,String... status){
        return null;
    }

    public Object queryArticlesByUser(int userId,boolean preview,Pageable pageable,String... status){
        return null;
    }
}
