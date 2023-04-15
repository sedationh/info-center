package com.example.api;

import lombok.Getter;

@Getter
public enum ApiCode {
    SUCCESS(200, "请求成功"),
    BAD_REQUEST(400, "请求参数错误"),
    UNAUTHORIZED(401, "无权限访问"),
    FORBIDDEN(403, "禁止访问"),
    NOT_FOUND(404, "请求资源不存在"),
    METHOD_NOT_ALLOWED(405, "请求方法不允许"),
    SERVER_ERROR(500, "服务器内部错误");

    private final int code;
    private final String message;

    ApiCode(int code, String message) {
        this.code = code;
        this.message = message;
    }
}