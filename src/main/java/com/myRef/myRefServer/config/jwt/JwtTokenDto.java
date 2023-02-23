package com.myRef.myRefServer.config.jwt;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class JwtTokenDto {
    private String access_token;
    private String refresh_token;
    private long expiration_time;
}
