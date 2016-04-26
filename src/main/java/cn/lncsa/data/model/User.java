package cn.lncsa.data.model;

import cn.lncsa.data.factory.UserDAO;
import org.bson.Document;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by catten on 16/1/15.
 */
public class User extends DataObject implements Serializable{

    private String name;
    private String password;
    private ArrayList<String> rights;
    private String contactInfo;

    private static UserDAO dao = new UserDAO();

    public User(Document doDoc) {
        apply(doDoc);
    }

    public User(){

    }

    public synchronized static UserDAO getDao() {
        return dao;
    }

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

    public String getContactInfo() {
        return contactInfo;
    }

    public void setContactInfo(String contactInfo) {
        this.contactInfo = contactInfo;
    }

    @Override
    public Document toDocument() {
        return new Document()
                .append("name",name)
                .append("password",password)
                .append("rights",rights)
                .append("contactInfo",contactInfo);
    }

    @Override
    public void apply(Document doDoc) {
        objectId = doDoc.getObjectId("_id");
        name = doDoc.getString("name");
        password = doDoc.getString("password");
        rights = doDoc.get("rights",ArrayList.class);
        contactInfo = doDoc.getString("contactInfo");
    }

}
