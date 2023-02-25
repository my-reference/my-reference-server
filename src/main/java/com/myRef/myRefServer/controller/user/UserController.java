package com.myRef.myRefServer.controller.user;

import com.myRef.myRefServer.config.jwt.JwtTokenDto;
import com.myRef.myRefServer.config.jwt.RegenarateTokenDto;
import com.myRef.myRefServer.controller.exception.UserNotFoundException;
import com.myRef.myRefServer.domain.user.DTO.UserLoginDto;
import com.myRef.myRefServer.domain.user.DTO.UserProfileDto.UserProfileResDto;
import com.myRef.myRefServer.domain.user.DTO.UserRegisterDto;
import com.myRef.myRefServer.domain.user.entity.User;
import com.myRef.myRefServer.service.user.UserServiceImpl;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
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

    @GetMapping("/profile")
    public UserProfileResDto profile(@AuthenticationPrincipal UserDetails userDetails) throws UserNotFoundException {
        System.out.println("userDetails = " + userDetails);
        User userDetail = userService.findByEmail(userDetails.getUsername())
                .orElseThrow(() -> new UserNotFoundException());

        return UserProfileResDto.builder()
                .userEmail(userDetail.getUserEmail())
                .userNickname(userDetail.getUserNickname())
                .build();
    }

}
