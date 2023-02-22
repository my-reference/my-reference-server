package com.myRef.myRefServer.domain.user.DTO;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;


@Data
@Getter
public class UserLoginDto {

    @Email(message = "올바른 이메일 형식이 아닙니다.")
    @NotEmpty(message = "이메일을 입력해주세요.")
    private String userEmail;

    @NotEmpty(message = "비밀번호를 입력해주세요.")
    private String userPassword;

    @Builder
    public UserLoginDto(String userEmail, String userPassword) {
        this.userEmail = userEmail;
        this.userPassword = userPassword;
    }
}
