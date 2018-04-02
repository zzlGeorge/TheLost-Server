package com.george.web.exception.ex;

/**
 * @author : George
 *         Description :
 *         Date : Created in 16:36 2018/3/29
 *         Modified By :
 */
public class CustomException extends RuntimeException {
    //异常信息
    private String message;

    public CustomException(String message) {
        super(message);
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
