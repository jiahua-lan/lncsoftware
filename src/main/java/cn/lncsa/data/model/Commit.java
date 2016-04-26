package cn.lncsa.data.model;

import org.bson.Document;
import org.bson.types.ObjectId;

import java.util.Date;

/**
 * Created by catten on 16/3/4.
 */
public class Commit extends DataObject {

    private ObjectId userId;
    private ObjectId articleId;
    private String contents;
    private Date date;

    public ObjectId getUserId() {
        return userId;
    }

    public void setUserId(ObjectId userId) {
        this.userId = userId;
    }

    public ObjectId getArticleId() {
        return articleId;
    }

    public void setArticleId(ObjectId articleId) {
        this.articleId = articleId;
    }

    public String getContents() {
        return contents;
    }

    public void setContents(String contents) {
        this.contents = contents;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Commit(Document comDoc) {
        apply(comDoc);
    }

    public Commit() {

    }

    @Override
    public Document toDocument() {
        return new Document()
                .append("userId", userId)
                .append("articleId", articleId)
                .append("contents", contents)
                .append("date", date);
    }

    @Override
    public void apply(Document doDoc) {
        objectId = doDoc.getObjectId("_id");
        userId = doDoc.getObjectId("userId");
        articleId = doDoc.getObjectId("objectId");
        contents = doDoc.getString("contents");
        if (objectId != null && date == null) {
            date = objectId.getDate();
        } else {
            date = doDoc.getDate("date");
        }
    }
}
