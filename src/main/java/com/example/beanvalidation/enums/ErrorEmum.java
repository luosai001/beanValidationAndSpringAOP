package com.example.beanvalidation.enums;

/**
 * Created by sai.luo on 2017-9-19.
 */
public enum  ErrorEmum
{
    DataError(0,"数据错误");

    private int code ;
    private String message ;

    ErrorEmum(int code, String message) {
        this.code = code;
        this.message = message;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
