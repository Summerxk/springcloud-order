package cn.tamilin.springcloud.order.exception;

/**
 * @ClassName BusinessExceprion
 * @Desciption TODO
 * @Author summer
 * @Date 2019/1/7 23:56
 */
public class BusinessException extends RuntimeException{

    public BusinessException() {
    }

    public BusinessException(String message) {
        super(message);
    }

    public BusinessException(Throwable cause) {
        super(cause);
    }

    public BusinessException(String message, Throwable cause) {
        super(message, cause);
    }

    public BusinessException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
