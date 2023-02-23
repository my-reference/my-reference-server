package com.myRef.myRefServer.config.jwt;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class RegenarateTokenDto {
    private String refresh_token;
}
