package cn.lncsoftware.data;

/**
 * Created by catten on 16/1/15.
 */
public class Bulletin {
    private String objectID;

    private String type;
    private String context;

    /**
     * Bulletin object
     * @param type Type of this bulletin
     * @param context Context
     */
    public Bulletin(String type, String context) {
        this.type = type;
        this.context = context;
    }

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
}
