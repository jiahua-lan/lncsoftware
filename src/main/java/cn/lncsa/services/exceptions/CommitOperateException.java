package cn.lncsa.services.exceptions;

/**
 * Created by catten on 16/7/2.
 */
public class CommitOperateException extends Exception {
    public CommitOperateException() {
    }

    public CommitOperateException(String message) {
        super(message);
    }

    public CommitOperateException(String message, Throwable cause) {
        super(message, cause);
    }
}
