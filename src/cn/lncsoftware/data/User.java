package cn.lncsoftware.data;

/**
 * Created by catten on 16/1/15.
 */
public class User {
    private String objectID;

    private String name;
    private String password;
    private String[] rights;
    private long timestamp;

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

    public String[] getRights() {
        return rights;
    }

    public void setRights(String[] rights) {
        this.rights = rights;
    }

    public long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(long timestamp) {
        this.timestamp = timestamp;
    }

    public String getObjectID() {
        return objectID;
    }

    public void setObjectID(String objectID) {
        this.objectID = objectID;
    }
}
