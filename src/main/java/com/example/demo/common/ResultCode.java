package com.example.demo.common;

public enum ResultCode {

    SUCCESS(1, "操作成功"),
    FAILURE(0, "操作失败"),
    UNAUTHORIZED(401, "未登录或token已过期");

    private final int code;
    private final String message;

    private ResultCode(int code, String message) {
        this.code = code;
        this.message = message;
    }

    public int getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }
}
