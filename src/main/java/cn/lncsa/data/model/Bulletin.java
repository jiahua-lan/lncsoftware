package cn.lncsa.data.model;

import cn.lncsa.data.factory.BulletinDAO;
import org.bson.Document;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by catten on 16/1/15.
 */
public class Bulletin extends DataObject implements Serializable {

    private static BulletinDAO dao = new BulletinDAO();

    private String type;
    private String context;
    private Date date;
    private String imageLink;

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    private String link;

    public Bulletin(Document doDoc) {
        apply(doDoc);
    }

    public Bulletin(){

    }

    public static BulletinDAO getDao() {
        return dao;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
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

    @Override
    public Document toDocument() {
        Document document = new Document()
                .append("type",type)
                .append("context",context)
                .append("date",date);
        if(link != null) document.append("link",link);
        if(imageLink != null) document.append("imageLink",imageLink);
        return document;
    }

    @Override
    public void apply(Document doDoc) {
        objectId = doDoc.getObjectId("_id");
        type = doDoc.getString("type");
        context = doDoc.getString("context");
        date = doDoc.getDate("date");
        link = doDoc.getString("link");
        imageLink = doDoc.getString("imageLink");
    }

    public String getImageLink() {
        return imageLink;
    }

    public void setImageLink(String imageLink) {
        this.imageLink = imageLink;
    }
}
