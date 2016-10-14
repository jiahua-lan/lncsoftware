package cn.lncsa.services.exceptions;

/**
 * Created by cattenlinger on 2016/9/29.
 */
public class TaggingOperateException extends Exception {
    public TaggingOperateException() {
        super();
    }

    public TaggingOperateException(String message) {
        super(message);
    }

    public TaggingOperateException(String message, Throwable cause) {
        super(message, cause);
    }
}
