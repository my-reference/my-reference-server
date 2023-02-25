package com.myRef.myRefServer.domain.user.DTO;

import lombok.Builder;
import lombok.Data;

public class UserProfileDto {

    @Data
    @Builder
    public static class UserProfileReqDto {
        private String userNickname;
    }

    @Data
    @Builder
    public static class UserProfileResDto {
        private String userEmail;
        private String userNickname;
    }

}
