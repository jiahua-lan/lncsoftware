package cn.lncsa.common.exceptions;

/**
 * Created by catten on 16/7/1.
 */
public class ArticleOperateException extends Exception {
    public ArticleOperateException() {
    }

    public ArticleOperateException(String message) {
        super(message);
    }

    public ArticleOperateException(String message, Throwable cause) {
        super(message, cause);
    }
}
