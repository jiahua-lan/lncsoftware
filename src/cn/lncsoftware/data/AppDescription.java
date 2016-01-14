package cn.lncsoftware.data;

/**
 * Created by catten on 16/1/15.
 */
public class AppDescription {
    private String objectID;

    private String title;
    private String description;
    private String ImageCode;
    private String link;
    private String status;
    private long timestamp;
    private String operator;

    /**
     * Create a App-Description object
     * @param title Title
     * @param description Descriptions
     * @param imageCode Path of the app-icon
     * @param link The link to the app
     * @param status Status of the app, if write "invisible" it will be hide, if "offline" the link will disable, "online" is default.
     * @param timestamp When the app create
     * @param operator The creator;
     */
    public AppDescription(String title, String description, String imageCode, String link, String status, long timestamp, String operator) {
        this.title = title;
        this.description = description;
        ImageCode = imageCode;
        this.link = link;
        this.status = status;
        this.timestamp = timestamp;
        this.operator = operator;
    }

    public AppDescription(){
        //Empty object
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
        return ImageCode;
    }

    public void setImageCode(String imageCode) {
        ImageCode = imageCode;
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

    public long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(long timestamp) {
        this.timestamp = timestamp;
    }

    public String getOperator() {
        return operator;
    }

    public void setOperator(String operator) {
        this.operator = operator;
    }

    public String getObjectID() {
        return objectID;
    }

    public void setObjectID(String objectID) {
        this.objectID = objectID;
    }
}
