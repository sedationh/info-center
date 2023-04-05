package com.info_center.server.api;

import lombok.Builder;
import lombok.Data;
import org.apache.commons.lang3.StringUtils;

@Data
@Builder
public class ApiResult<T> {
    private static final long serialVersionUID = 1L;

    private int code;

    private String message;

    private T data;

    private static <T> ApiResult<T> result(ApiCode apiCode, String message, T data) {
        String apiMessage = apiCode.getMessage();
        if (StringUtils.isNotBlank(message)) {
            apiMessage = message;
        }
        return (ApiResult<T>) ApiResult.builder()
                .code(apiCode.getCode())
                .message(apiMessage)
                .data(data)
                .build();
    }

    public static <T> ApiResult<T> result(ApiCode apiCode, T data) {
        return result(apiCode, null, data);
    }

    public static <T> ApiResult<T> ok(T data) {
        return result(ApiCode.SUCCESS, data);
    }
}
