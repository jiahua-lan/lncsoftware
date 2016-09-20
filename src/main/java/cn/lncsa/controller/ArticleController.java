package cn.lncsa.controller;

import cn.lncsa.data.dto.Previewable;
import cn.lncsa.data.dto.impl.ArticlePreview;
import cn.lncsa.data.model.article.Article;
import cn.lncsa.data.model.article.Tag;
import cn.lncsa.services.IArticleServices;
import org.hibernate.criterion.Order;
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
@RequestMapping("/articles")
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
    public
    @ResponseBody
    List latestArticleList(
            @RequestParam(value = "preview", required = false) boolean preview) {
        List<Article> articles = articleServices.getLatestArticle();
        if (preview) {
            return convertToPreviewList(articles);
        }
        return articleServices.getLatestArticle();
    }

    /**
     * Get Articles
     *
     * @param page    which page for
     * @param status  article status for. if not set, "shown" will be default
     * @param tags    only articles match a list of tags
     * @param preview get full articles or just get preview information
     * @param sortBy  two options : "createDate" or "lastModifiedDate", default value is "createDate"
     * @return a Map of result, include those keys:
     * pageCount : sum of pages
     * currentPage : current position
     * content : article or just previews
     */
    @RequestMapping(value = "/list/{page}", method = RequestMethod.GET)
    public
    @ResponseBody
    Map<String, Object> getArticlePage(
            @PathVariable("page") int page,
            @RequestParam(value = "status", required = false) String[] status,
            @RequestParam(value = "tag", required = false) String[] tags,
            @RequestParam(value = "preview", required = false) boolean preview,
            @RequestParam(value = "sortBy", defaultValue = "createDate", required = false) String sortBy) {

        //Check sort option, and create a pageRequest with it
        String sortOption = checkSortOption(sortBy);

        //Initialize status list
        String[] statusList = initializeStatusList(status);

        //If query with tags, will use the method which query with tags
        if (tags != null) {
            /*
                Attention, the DAO-API which ArticleServices.getArticleByTags() uses is using ArticleTag to query, so
                we should change the sort option to fit the JPA query in DAO.
            */
            Pageable pageable = new PageRequest(page, 5, new Sort(Sort.Direction.DESC, "article." + sortOption));

            return convertPageToHashMap(articleServices.getArticleByTags(
                    getTagListByStrings(Arrays.asList(tags)),
                    pageable,
                    statusList
            ), preview);
        }
        Pageable pageable = new PageRequest(page, 5, new Sort(Sort.Direction.DESC, sortOption));
        return convertPageToHashMap(articleServices.getAllArticle(pageable, statusList), preview);
    }

    @RequestMapping(value = "/user/{userId}/{page}", method = RequestMethod.GET)
    public
    @ResponseBody
    Map<String, Object> showUserArticle(
            @PathVariable("userId") Integer userId,
            @PathVariable("page") int page,
            @RequestParam(value = "status", required = false) String[] status,
            @RequestParam(value = "preview", required = false) boolean preview,
            @RequestParam(value = "sortBy", required = false) String sortBy) {

        Pageable pageable = new PageRequest(
                page,
                5,
                new Sort(Sort.Direction.DESC, checkSortOption(sortBy)));

        String[] statusList = initializeStatusList(status);

        return convertPageToHashMap(articleServices.getArticleByUserId(
                userId,
                pageable,
                statusList), preview);
    }

    /*
    *
    *  Private procedure
    *
    *
    */

    //Check sort option, and create a pageRequest with it
    private String checkSortOption(String sortBy) {
        if(sortBy == null) return "createDate";
        switch (sortBy) {
            case "createDate":
            case "lastModifiedDate":
                return sortBy;
            default:
                return "createDate";
        }
    }

    //Check status list, if is null then return a new String array containing "shown"
    private String[] initializeStatusList(String[] originList) {
        return originList == null ? new String[]{"shown"} : originList;
    }

    //Get tags by tag's name
    private List<Tag> getTagListByStrings(List<String> tags) {
        List<Tag> tagList = new LinkedList<>();
        for (String tagName : tags) {
            Tag tag = articleServices.getTagByTitle(tagName);
            if (tag != null) tagList.add(tag);
        }
        return tagList;
    }

    //Get base information from Page<Article> and fill into HashMap, set "preview" to true can get article preview info only
    private HashMap<String, Object> convertPageToHashMap(Page<Article> articles, boolean preview) {
        HashMap<String, Object> hashMap = new HashMap<>();
        hashMap.put("pageCount", articles.getTotalPages());
        hashMap.put("currentPage", articles.getNumber());
        hashMap.put("content", preview ? convertToPreviewList(articles.getContent()) : articles.getContent());
        return hashMap;
    }

    //Use for convert articles to a preview list.
    private List<Previewable> convertToPreviewList(List<Article> articles) {
        List<Previewable> previewables = new LinkedList<>();

        for (Article article : articles) {
            ArticlePreview articlePreview = new ArticlePreview();
            articlePreview.setAuthor(article.getAuthor().getName());
            articlePreview.setDate(article.getLastModifiedDate());
            articlePreview.setContent(article.getPreviewSentences());
            previewables.add(articlePreview);
        }
        return previewables;
    }
}
