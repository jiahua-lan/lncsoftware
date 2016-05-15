package cn.lncsa.actions;

import cn.lncsa.data.ArticleInfo;
import cn.lncsa.data.model.Article;
import cn.lncsa.data.model.User;
import com.opensymphony.xwork2.ActionSupport;
import org.apache.struts2.interceptor.RequestAware;
import org.apache.struts2.interceptor.SessionAware;

import java.util.List;
import java.util.Map;

/**
 * Created by catten on 16/5/11.
 */
public class ArticleAdmin extends ActionSupport implements RequestAware, SessionAware {

    private String keyword;

    public String show(){
        String chkPer = checkPermission();
        if(chkPer != null) return chkPer;
        return "success";
    }

    public String search(){
        String chkPer = checkPermission();
        if (chkPer != null) return chkPer;

        if(keyword != null || !keyword.trim().equals("")){
            List<Article> articles = Article.getDao().query(keyword);
            if(articles != null){
                requestContext.put("articleList", ArticleInfo.convertArticleList(articles));
            }
        }
        return "success";
    }

    private String checkPermission(){
        User user = (User) sessionContext.get("passport");
        if(user == null){
            return "login";
        }
        boolean flag = false;
        for (String s : user.getRights()){
            if(s.equals("admin")){
                flag = true;
                break;
            }
        }
        if(flag) {
            return null;
        } else {
            requestContext.put("message","Permission Denied");
            return "error";
        }
    }

    private Map<String,Object> sessionContext;
    @Override
    public void setSession(Map<String, Object> map) {
        sessionContext = map;
    }

    private Map<String,Object> requestContext;
    @Override
    public void setRequest(Map<String, Object> map) {
        requestContext = map;
    }

    public String getKeyword() {
        return keyword;
    }

    public void setKeyword(String keyword) {
        this.keyword = keyword;
    }
}
