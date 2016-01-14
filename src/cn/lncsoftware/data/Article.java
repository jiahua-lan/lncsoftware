package cn.lncsoftware.data;

import java.util.Date;

/**
 * Created by catten on 16/1/15.
 */

public class Article {
    private String objectID;

    private String title;
    private String context;
    private long timeStamp;
    private String author;
    private String previewSentences;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContext() {
        return context;
    }

    public void setContext(String context) {
        this.context = context;
    }

    public long getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(long timeStamp) {
        this.timeStamp = timeStamp;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getPreviewSentences() {
        if(previewSentences == null || "".equals(previewSentences)){
            if(context.length() < 100){
                previewSentences = context;
            }else{
                previewSentences = String.format("%s ...(total %s words)",context.substring(0,100),context.length());
            }
        }
        return previewSentences;
    }

    public void setPreviewSentences(String previewSentences) {
        this.previewSentences = previewSentences;
    }

    public String getObjectID() {
        return objectID;
    }

    public void setObjectID(String objectID) {
        this.objectID = objectID;
    }
}
