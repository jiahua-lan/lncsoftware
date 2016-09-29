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
@RequestMapping("/articles")
public class ArticleController {
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

    /**
     * Get latest new articles
     *
     * @param preview not required. Set true will return preview list
     * @return preview = true : return List<IArticlePreview> ; preview = false : return List<Article>
     */
    @RequestMapping(value = "/list/latest", method = RequestMethod.GET)
    public
    @ResponseBody
    List latestArticleList(@RequestParam(value = "preview", required = false) boolean preview) {
        return null;
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
                Attention, the DAO-API which ArticleServices.getArticleByTag() uses is using ArticleTag to query, so
                we should change the sort option to fit the JPA query in DAO.
            */
            Pageable pageable = new PageRequest(page, 5, new Sort(Sort.Direction.DESC, "article." + sortOption));

            return convertPageToHashMap(articleServices.getArticleByTag(
                    taggingServices.getListOfTagByName(Arrays.asList(tags)),
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

    //Get base information from Page<Article> and fill into HashMap, set "preview" to true can get article preview info only
    private HashMap<String, Object> convertPageToHashMap(Page<Article> articles, boolean preview) {
        HashMap<String, Object> hashMap = new HashMap<>();
        hashMap.put("pageCount", articles.getTotalPages());
        hashMap.put("currentPage", articles.getNumber());
        hashMap.put("content", preview ? convertToPreviewList(articles.getContent()) : articles.getContent());
        return hashMap;
    }

    //Use for convert articles to a preview list.
    private List<IArticlePreview> convertToPreviewList(List<Article> articles) {
        List<IArticlePreview> previewables = new LinkedList<>();

        for (Article article : articles) {
            ArticlePreview articlePreview = new ArticlePreview();
            try {
                articlePreview.setAuthor(userServices.get(article.getAuthorId()).getNickName());
            } catch (UserOperateException e) {
                articlePreview.setAuthor(null);
            }
            articlePreview.setDate(article.getLastModifiedDate());
            articlePreview.setContent(article.getPreviewSentences());
            previewables.add(articlePreview);
        }
        return previewables;
    }
}
