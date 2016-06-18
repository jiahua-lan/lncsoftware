package cn.lncsa.actions;

import cn.lncsa.data.dao.IArticleDAO;
import cn.lncsa.data.dao.IBulletinDAO;
import cn.lncsa.data.model.Article;
import com.opensymphony.xwork2.ActionSupport;
import org.apache.struts2.interceptor.RequestAware;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import java.util.Map;

/**
 * Created by catten on 16/4/23.
 */
public class IndexAction extends ActionSupport implements RequestAware {

    Map<String, Object> requestContext;

    private IBulletinDAO bulletinDAO;
    private IArticleDAO articleDAO;

    @Autowired
    public void setBulletinDAO(IBulletinDAO bulletinDAO) {
        this.bulletinDAO = bulletinDAO;
    }

    @Autowired
    public void setArticleDAO(IArticleDAO articleDAO) {
        this.articleDAO = articleDAO;
    }

    @Override
    public void setRequest(Map<String, Object> map) {
        requestContext = map;
    }

    public String execute() {
        requestContext.put("bulletin", bulletinDAO.getMainPageBulletin());
        requestContext.put("topBoard", bulletinDAO.getMainPageTopBulletin());
        Page<Article> thePage = articleDAO.findAll(new PageRequest((int) ((articleDAO.count()) / 5 - 1),5));
        requestContext.put("articleList", thePage.getContent());
        return "success";
    }
}
