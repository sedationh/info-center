package com.example.controller;

import com.example.api.ApiCode;
import com.example.api.ApiResult;
import com.example.domin.constant.AppConstants;
import com.example.domin.params.AuthRequestParams;
import com.example.security.JwtUtils;
import com.example.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

@RestController
@RequestMapping("/api/auth")
@AllArgsConstructor
public class AuthController {
    private final UserService userService;
    final PasswordEncoder passwordEncoder;
    final AuthenticationManager authenticationManager;

    final JwtUtils jwtUtils;


    @GetMapping("/isUserSet")
    public ApiResult<Boolean> isUserSet() {
        return ApiResult.ok(userService.isUserSet());
    }

    @PostMapping("/signup")
    public ApiResult signup(@RequestBody AuthRequestParams authRequest) {
        String encodedPassword = passwordEncoder.encode(authRequest.getPassword());

        try {
            userService.createUser(authRequest.getUsername(), encodedPassword);
        } catch (Exception e) {
            return ApiResult.error(ApiCode.CUSTOM_ERROR.getCode(), e.getMessage());
        }

        return ApiResult.ok("ok");
    }


    @PostMapping("/signin")
    public ApiResult signin(@RequestBody AuthRequestParams authRequest, HttpServletResponse response) {
        // 需要去 WebSecurityConfig 中配置
        UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(authRequest.getUsername(), authRequest.getPassword());
        Authentication authentication = authenticationManager.authenticate(token);
        String jwt = jwtUtils.generateJwtToken(authentication);

        addJwtToCookie(response, jwt);

        return ApiResult.ok("ok");
    }

    private void addJwtToCookie(HttpServletResponse response, String jwt) {
        Cookie cookie = new Cookie(AppConstants.AUTH_TOKEN_COOKIE_NAME, jwt);
        cookie.setHttpOnly(true);
        cookie.setPath("/");
        cookie.setMaxAge(jwtUtils.getJwtExpirationSeconds());
        response.addCookie(cookie);
    }

}
