package com.info_center.server.controller;

import com.info_center.server.api.ApiResult;
import com.info_center.server.service.UserService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
public class AuthController {
    private final UserService userService;

    public AuthController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/isUserSet")
    public ApiResult<Boolean> isUserSet() {
        return ApiResult.ok(userService.isUserSet());
    }
}
