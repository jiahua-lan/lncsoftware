package cn.lncsa.data;

import cn.lncsa.data.factory.UserDAO;
import cn.lncsa.data.model.Article;
import cn.lncsa.data.model.User;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by catten on 16/4/25.
 */
public class ArticleInfo{
    private String authorName;
    private String createDate;

    private String id;
    private String title;
    private String previewSentences;
    private String status;
    private List<String> tags;

    private static SimpleDateFormat simpleDateFormat = new SimpleDateFormat("YYYY-MM-DD hh:mm");

    public ArticleInfo(Article article){
        UserDAO userDAO = User.getDao();
        authorName = userDAO.read(article.getAuthor()).getName();
        createDate = simpleDateFormat.format(article.getDate());
        title = article.getTitle();
        previewSentences = article.getPreviewSentences();
        tags = article.getTags();
        status = article.getStatus();
        id = article.getObjectId().toHexString();
    }

    public ArticleInfo(){

    }

    public String getAuthorName() {
        return authorName;
    }

    public String getCreateDate() {
        return createDate;
    }

    public String getTitle() {
        return title;
    }

    public String getPreviewSentences() {
        return previewSentences;
    }

    public String getStatus() {
        return status;
    }

    public List<String> getTags() {
        return tags;
    }

    public static List<ArticleInfo> convertArticleList(List<Article> list){
        List<ArticleInfo> l = new ArrayList<>();
        for(Article article : list){
            l.add(new ArticleInfo(article));
        }
        return l;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
