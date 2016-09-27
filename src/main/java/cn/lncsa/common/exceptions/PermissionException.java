package cn.lncsa.common.exceptions;

/**
 * Created by cattenlinger on 2016/9/26.
 */
public class PermissionException extends Exception {
    public PermissionException() {
        super();
    }

    public PermissionException(String message) {
        super(message);
    }

    public PermissionException(String message, Throwable cause) {
        super(message, cause);
    }
}
