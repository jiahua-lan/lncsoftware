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
