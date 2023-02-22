package com.myRef.myRefServer.config;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class JwtTokenDto {
    private String access_token;
}
