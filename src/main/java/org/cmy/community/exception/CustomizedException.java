package org.cmy.community.exception;

public class CustomizedException extends RuntimeException {
    private String message;
    private Integer code;


    public CustomizedException(ICustomizedErrorCode errorCode) {
        this.message = errorCode.getMessage();
        this.message = errorCode.getMessage();
    }

    @Override
    public String getMessage() {
        return message;
    }

    public Integer getCode() {
        return code;
    }
}
