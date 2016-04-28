package cn.lncsa.actions;

import cn.lncsa.data.ArticleInfo;
import cn.lncsa.data.model.Article;
import cn.lncsa.data.model.Bulletin;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import org.apache.struts2.interceptor.RequestAware;
import org.bson.types.ObjectId;
import org.pegdown.PegDownProcessor;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by catten on 16/4/25.
 */
public class ArticleAction extends ActionSupport implements RequestAware, ModelDriven<Article>{
    private static PegDownProcessor pegDownProcessor = new PegDownProcessor();

    private Article article = new Article();

    private String id;
    private String page;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPage() {
        return page;
    }

    public void setPage(String page) {
        this.page = page;
    }

    public String execute(){
        return "success";
    }

    //List articles
    public String list(){
        List<Article> articles;
        int pagePerList = 10;
        int pageNum;
        int totalPage;
        if(page == null){
            pageNum = 1;
        }else {
            if (!page.matches("\\d+")) {
                if("<<".equals(page))
                    pageNum = 1;
                else pageNum = Article.getDao().getPageCount();
            } else {
                pageNum = Integer.parseInt(page);
            }
        }
        articles = Article.getDao().getPage(pageNum);
        totalPage = Article.getDao().getPageCount();
        requestContext.put("articleList",ArticleInfo.convertArticleList(articles));
        requestContext.put("pageList",rendPageList(pageNum,totalPage,pagePerList));
        requestContext.put("currentPage",pageNum);
        requestContext.put("pageCount",totalPage);
        return "success";
    }

    //Rending article for shown
    public String rend(){
        try {
            article = Article.getDao().read(new ObjectId(id));
        }catch (Throwable e){
            return "error";
        }
        if(article == null) return "not-found";
        requestContext.put("renderedArticle",pegDownProcessor.markdownToHtml(article.getContext()));
        requestContext.put("article",new ArticleInfo(article));
        return "success";
    }

    private List<String> rendPageList(int position, int totalPage, int pagesPerList){
        List<String> list;
        //Only 1 page.
        if(position <= 1 && totalPage <= 1) {
            list = new ArrayList<>(1);
            list.add("1");
            return list;
        }

        //More than one page

        //Lesser than pages per list
        if(totalPage <= pagesPerList){
            list = new ArrayList<>(totalPage);
            for(int i = 0; i < totalPage; i++){
                list.add(String.valueOf(i+1));
            }
        }else{
            list = new ArrayList<>(pagesPerList);
            int start, end;
            int before = (int) Math.floor(pagesPerList / 2);
            if(position < before){
                start = 1;
                end = pagesPerList;
            }else {
                start = (int) (position - Math.floor(pagesPerList / 2));
                if(start == 0) start = 1;
                end = (start + pagesPerList) > totalPage ? totalPage : start + pagesPerList;
            }
            if(start != 1) list.add("<<");
            for(int i = start; i <= end; i++){
                list.add(String.valueOf(i));
            }
            if(end != totalPage) list.add(">>");
        }
        return list;
    }

    Map<String, Object> requestContext;
    @Override
    public void setRequest(Map<String, Object> map) {
        requestContext = map;
        requestContext.put("bulletin", Bulletin.getDao().getBulletinBoard("article"));
    }

    @Override
    public Article getModel() {
        return article;
    }
}
