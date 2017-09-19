package com.example.beanvalidation.exception;

import com.example.beanvalidation.enums.ErrorEmum;

/**
 * Created by sai.luo on 2017-9-19.
 */
public class WrapException extends RuntimeException {
    private int code ;
    private String message ;

    public WrapException(int code, String message) {
        this.code = code;
        this.message = message;
    }

    public WrapException(ErrorEmum errorEmum){
        this.code = errorEmum.getCode();
        this.message = errorEmum.getMessage();
    }
    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    @Override
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
