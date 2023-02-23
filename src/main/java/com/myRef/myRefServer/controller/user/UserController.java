package com.myRef.myRefServer.controller.user;

import com.myRef.myRefServer.config.jwt.JwtTokenDto;
import com.myRef.myRefServer.config.jwt.RegenarateTokenDto;
import com.myRef.myRefServer.domain.user.DTO.UserLoginDto;
import com.myRef.myRefServer.domain.user.DTO.UserRegisterDto;
import com.myRef.myRefServer.service.user.UserServiceImpl;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;


@RestController
@RequiredArgsConstructor
@RequestMapping( "/v1")
public class UserController {

    private final UserServiceImpl userService;

    @PostMapping( "/signup")
    public Long signUp(@Valid @RequestBody UserRegisterDto request) throws Exception {
        return userService.signUp(request);
    }

    @PostMapping("/login")
    public ResponseEntity<JwtTokenDto> logIn(@Valid @RequestBody UserLoginDto request) {
        return userService.logIn(request);
    }

    @PostMapping("/token/refresh")
    public ResponseEntity<JwtTokenDto> regenerateToken(@Validated RegenarateTokenDto refreshTokenDto) {
        return userService.regenerateToken(refreshTokenDto);
    }

}
