package cn.lncsa.actions;

import cn.lncsa.data.ArticleInfo;
import cn.lncsa.data.model.Article;
import com.opensymphony.xwork2.ActionSupport;
import org.apache.struts2.interceptor.RequestAware;

import java.util.List;
import java.util.Map;

/**
 * Created by catten on 16/4/25.
 */
public class ArticleAction extends ActionSupport implements RequestAware{

    public String execute(){
        return "success";
    }

    public String list(){
        String page = (String) requestContext.get("page");
        List<Article> articles;

        if(page == null || !page.matches("\\d+")){
            articles = Article.getDao().getLatestPage();
        }else{
            articles = Article.getDao().getPage(Integer.parseInt(page));
        }
        requestContext.put("articleList",ArticleInfo.convertArticleList(articles));
        return "success";
    }

    Map<String, Object> requestContext;
    @Override
    public void setRequest(Map<String, Object> map) {
        requestContext = map;
    }
}
