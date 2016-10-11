package cn.lncsa.common;

import java.util.Objects;

/**
 * Created by catten on 10/11/16.
 */
public class ResultObject {
    private boolean success;
    private String message;
    private Object content;

    public ResultObject() {
    }

    public ResultObject(boolean success, Object content){
        this.success = success;
        this.content = content;
    }

    public ResultObject(boolean success, String message) {
        this.success = success;
        this.message = message;
    }

    public ResultObject(boolean success) {
        this.success = success;
    }

    public ResultObject(boolean success, String message, Object content) {
        this.success = success;
        this.message = message;
        this.content = content;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Object getContent() {
        return content;
    }

    public void setContent(Object content) {
        this.content = content;
    }
}
