package cn.lncsa.controller;

import cn.lncsa.common.ResultObject;
import cn.lncsa.data.dao.article.IArticleDAO;
import cn.lncsa.data.model.article.ArticleStatus;
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

    // /articles/query/latest
    // count={int}
    // status={string}
    public Object queryLatestArticles(int count){
        List<Article> articles = articleServices.getLatest(count, ArticleStatus.Shown.name());
        return new ResultObject(true,articles);
    }

    // /articles/query/search
    // PageRequest
    // tag={int}
    // user={int}
    // pKeyword={String}
    // beginDate={Date}
    // endDate={Date}
    // dateType={IArticleDAO.dateType}
    // status={boolean}
    public Object queryArticles(Map<String,Object> model,Pageable pageable){
        return null;
    }

    // /articles/query/user/{userId}
    // PageRequest
    public Object queryArticles(Integer userId,Pageable pageable){
        return null;
    }
}
