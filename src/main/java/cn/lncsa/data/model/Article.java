package cn.lncsa.data.model;

import cn.lncsa.data.factory.ArticleDAO;
import org.bson.Document;
import org.bson.types.ObjectId;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by catten on 16/1/15.
 */

public class Article extends DataObject implements Serializable{

    private static ArticleDAO dao = new ArticleDAO();

    private String title;
    private String context;
    private Date date;
    private ObjectId author;
    private String previewSentences;
    private List<String> tags;

    public Article(Document doDoc) {
        apply(doDoc);
    }

    public Article(){

    }

    public synchronized static ArticleDAO getDao() {
        return dao;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    private String status;

    @Override
    public Document toDocument() {
        return new Document()
                .append("title",title)
                .append("context",context)
                .append("date",date)
                .append("author",author)
                .append("previewSentences",previewSentences)
                .append("status",status)
                .append("tags", tags);
    }

    @Override
    public void apply(Document doDoc) {
        objectId = doDoc.getObjectId("_id");
        title = doDoc.getString("title");
        context = doDoc.getString("context");
        date = doDoc.getDate("date");
        author = doDoc.getObjectId("author");
        previewSentences = doDoc.getString("previewSentences");
        status = doDoc.getString("status");
        tags = doDoc.get("tags",ArrayList.class);
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContext() {
        return context;
    }

    public void setContext(String context) {
        this.context = context;
    }

    public Date getDate() {
        if(date == null)
            if(objectId != null) date = objectId.getDate();
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public ObjectId getAuthor() {
        return author;
    }

    public void setAuthor(ObjectId author) {
        this.author = author;
    }

    public String getPreviewSentences() {
        if(previewSentences == null || "".equals(previewSentences)){
            if(context.length() < 100){
                previewSentences = context;
            }else{
                previewSentences = context.substring(0,100);
            }
        }
        return previewSentences;
    }

    public void setPreviewSentences(String previewSentences) {
        this.previewSentences = previewSentences;
    }

    public List<String> getTags() {
        return tags;
    }

    public void setTags(List<String> tags) {
        this.tags = tags;
    }
}
