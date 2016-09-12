package cn.lncsa.controller;

import cn.lncsa.data.dto.Previewable;
import cn.lncsa.data.dto.impl.ArticlePreview;
import cn.lncsa.data.model.article.Article;
import cn.lncsa.services.IArticleServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.LinkedList;
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

    /**
     * Get latest new articles
     *
     * @param preview not required. Set true will return preview list
     * @return preview = true : return List<Previewable> ; preview = false : return List<Article>
     */
    @RequestMapping(value = "/list/latest", method = RequestMethod.GET)
    public @ResponseBody List latestArticleList(
            @RequestParam(value = "preview", required = false) boolean preview){
        List<Article> articles = articleServices.getLatestArticle();
        if(preview){
            return convertToPreviewList(articles);
        }
        return articleServices.getLatestArticle();
    }

    @RequestMapping(value = "/list/{page}",method = RequestMethod.GET)
    public @ResponseBody List getArticlePage(
            @PathVariable("page") int page,
            @RequestParam(value = "preview",required = false) boolean preview){

        List<Article> articles = articleServices.getAllArticle(
                new PageRequest(page, 5, new Sort(Sort.Direction.DESC, "createDate")),
                "show").getContent();

        if(preview){
            List<Previewable> previewables = new LinkedList<>();
        }
        return convertToPreviewList(articles);
    }

    @RequestMapping(value = "/list/{page}",method = RequestMethod.POST)
    public @ResponseBody List getArticlePageWithPermission(
            @PathVariable("page") int page,
            @RequestParam(value = "preview",required = false) boolean preview){

        List<Article> articles = articleServices.getAllArticle(
                new PageRequest(page, 5, new Sort(Sort.Direction.DESC, "createDate")),
                "show").getContent();

        if(preview){
            List<Previewable> previewables = new LinkedList<>();
        }
        return convertToPreviewList(articles);
    }

    //Use for convert articles to a preview list.
    private List<Previewable> convertToPreviewList(List<Article> articles){
        List<Previewable> previewables = new LinkedList<>();

        for(Article article : articles){
            ArticlePreview articlePreview = new ArticlePreview();
            articlePreview.setAuthor(article.getAuthor().getName());
            articlePreview.setDate(article.getLastModifiedDate());
            articlePreview.setContent(article.getPreviewSentences());
            previewables.add(articlePreview);
        }

        return previewables;
    }
}
