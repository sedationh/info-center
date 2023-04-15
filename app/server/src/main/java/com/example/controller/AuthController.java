package com.example.controller;

import com.example.api.ApiResult;
import com.example.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
@AllArgsConstructor
public class AuthController {
    private final UserService userService;

    @GetMapping("/isUserSet")
    public ApiResult<Boolean> isUserSet() {
        return ApiResult.ok(userService.isUserSet());
    }
}
