package zhouxiong.demo.demo.infrastructure.exception;

/**
 * @Package zhouxiong.demo.demo.infrastructure.exception
 * @Description:
 * @author: zhouxiong
 * @create: 2021-04-23 21:51
 **/
public class LadOpenException extends RuntimeException {
    private static final long serialVersionUID = 1L;
    private ErrorInfo errorInfo;

    public LadOpenException() {
    }

    public LadOpenException(ErrorInfo errorInfo) {
        super(errorInfo.getDesc());
        this.errorInfo = errorInfo;
    }

    public LadOpenException(ErrorInfo errorInfo, Throwable cause) {
        super(cause);
        this.errorInfo = errorInfo;
    }

    public LadOpenException(String message) {
        super(message);
    }

    public LadOpenException(Throwable cause) {
        super(cause);
    }

    public LadOpenException(String message, Throwable cause) {
        super(message, cause);
    }

    public LadOpenException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }

    public ErrorInfo getErrorInfo() {
        return errorInfo;
    }
}
