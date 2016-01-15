package cn.lncsoftware.data;

import java.util.ArrayList;
import java.util.Date;

/**
 * Created by catten on 16/1/15.
 */
public class User {
    private String objectID;

    private String name;
    private String password;
    private ArrayList<String> rights;
    private Date regDate;
    private String contactInfo;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public ArrayList<String> getRights() {
        return rights;
    }

    public void setRights(ArrayList<String> rights) {
        this.rights = rights;
    }

    public Date getRegDate() {
        return regDate;
    }

    public void setRegDate(Date regDate) {
        this.regDate = regDate;
    }

    public String getObjectID() {
        return objectID;
    }

    public void setObjectID(String objectID) {
        this.objectID = objectID;
    }

    public String getContactInfo() {
        return contactInfo;
    }

    public void setContactInfo(String contactInfo) {
        this.contactInfo = contactInfo;
    }
}
