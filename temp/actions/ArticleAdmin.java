package cn.lncsa.actions;

import cn.lncsa.data.Passport;
import cn.lncsa.data.dao.IArticleDAO;
import cn.lncsa.data.model.Article;
import cn.lncsa.data.model.Right;
import com.opensymphony.xwork2.ActionSupport;
import org.apache.struts2.interceptor.RequestAware;
import org.apache.struts2.interceptor.SessionAware;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Map;

/**
 * Created by catten on 16/5/11.
 */
public class ArticleAdmin extends ActionSupport implements RequestAware, SessionAware {

    private String keyword;

    private IArticleDAO articleDAO;

    @Autowired
    public void setArticleDAO(IArticleDAO articleDAO) {
        this.articleDAO = articleDAO;
    }

    public String show(){
        String chkPer = checkPermission();
        if(chkPer != null) return chkPer;
        return "success";
    }

    public String search(){
        String chkPer = checkPermission();
        if (chkPer != null) return chkPer;

        if(keyword != null || !keyword.trim().equals("")){
            List<Article> articles = articleDAO.findByTitleLike(keyword);
            if(articles != null){
                requestContext.put("articleList", articles);
            }
        }
        return "success";
    }

    private String checkPermission(){
        Passport passport = (Passport) sessionContext.get("passport");
        if(passport == null){
            return "login";
        }
        boolean flag = false;
        for (Right right : passport.getRights()){
            if(right.getName().equals("admin")){
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
