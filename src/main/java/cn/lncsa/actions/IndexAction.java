package cn.lncsa.actions;

import cn.lncsa.data.ArticleInfo;
import cn.lncsa.data.model.Article;
import cn.lncsa.data.model.Bulletin;
import com.opensymphony.xwork2.ActionSupport;
import org.apache.struts2.interceptor.RequestAware;

import java.util.Map;

/**
 * Created by catten on 16/4/23.
 */
public class IndexAction extends ActionSupport implements RequestAware {

    Map<String, Object> requestContext;

    @Override
    public void setRequest(Map<String, Object> map) {
        requestContext = map;
    }

    public String execute() {
        requestContext.put("bulletin", Bulletin.getDao().getBulletinBoard("mainPage"));
        requestContext.put("topBoard", Bulletin.getDao().getBulletinBoard("mainPageTopBoard"));
        requestContext.put("articleList", ArticleInfo.convertArticleList(Article.getDao().getLatestPage()));
        return "success";
    }
}
