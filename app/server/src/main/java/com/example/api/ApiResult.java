package com.example.api;


import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ApiResult<T> {

    private int code;

    private String message;

    private T data;

    public static <T> ApiResult<T> ok(T body) {
        return new ApiResult<T>(ApiCode.SUCCESS.getCode(), ApiCode.SUCCESS.getMessage(), body);
    }
}
