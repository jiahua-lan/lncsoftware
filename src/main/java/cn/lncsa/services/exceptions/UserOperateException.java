package cn.lncsa.services.exceptions;

/**
 * Created by catten on 16/6/19.
 */
public class UserOperateException extends Exception {
    public UserOperateException() {
    }

    public UserOperateException(String message) {
        super(message);
    }

    public UserOperateException(String message, Throwable cause) {
        super(message, cause);
    }
}
