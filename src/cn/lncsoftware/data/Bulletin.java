package cn.lncsoftware.data;

import java.util.Date;

/**
 * Created by catten on 16/1/15.
 */
public class Bulletin {
    private String objectID;

    private String type;
    private String context;
    private Date date;

    public String getObjectID() {
        return objectID;
    }

    public void setObjectID(String objectID) {
        this.objectID = objectID;
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
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}
