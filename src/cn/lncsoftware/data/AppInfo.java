package cn.lncsoftware.data;

import cn.lncsoftware.data.factory.AppInfoDAO;
import org.bson.Document;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by catten on 16/1/15.
 */
public class AppInfo extends DataObject implements Serializable{

    private static AppInfoDAO dao = new AppInfoDAO();

    private String title;
    private String description;
    private String imageCode;
    private String link;
    private String status;
    private Date date;

    public AppInfo(Document doDoc) {
        apply(doDoc);
    }

    public AppInfo(){

    }

    public static AppInfoDAO getDao() {
        return dao;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * Get path of the app-icon
     * @return path to the image
     */
    public String getImageCode() {
        return imageCode;
    }

    public void setImageCode(String imageCode) {
        this.imageCode = imageCode;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Date getDate() {
        if(date == null){
            if(objectId != null) date = objectId.getDate();
        }
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    @Override
    public Document toDocument() {
        return new Document()
                .append("title",title)
                .append("description",description)
                .append("imageCode",imageCode)
                .append("link",link)
                .append("status",status)
                .append("date",date);
    }

    @Override
    public void apply(Document doDoc) {
        objectId = doDoc.getObjectId("_id");
        title = doDoc.getString("title");
        description = doDoc.getString("description");
        imageCode = doDoc.getString("imageCode");
        link = doDoc.getString("link");
        status = doDoc.getString("status");
        date = doDoc.getDate("date");
    }
}
