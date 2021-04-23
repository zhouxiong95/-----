package zhouxiong.demo.demo.infrastructure.exception;

/**
 * @Package zhouxiong.demo.demo.infrastructure.exception
 * @Description:
 * @author: zhouxiong
 * @create: 2021-04-23 21:55
 **/
public class CheckedException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    public CheckedException() {
    }

    public CheckedException(String message) {
        super(message);
    }

    public CheckedException(Throwable cause) {
        super(cause);
    }

    public CheckedException(String message, Throwable cause) {
        super(message, cause);
    }

    public CheckedException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }

}
