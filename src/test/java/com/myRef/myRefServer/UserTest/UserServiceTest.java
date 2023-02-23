package com.myRef.myRefServer.UserTest;

import com.myRef.myRefServer.config.jwt.JwtTokenDto;
import com.myRef.myRefServer.domain.user.DTO.UserLoginDto;
import com.myRef.myRefServer.domain.user.DTO.UserRegisterDto;
import com.myRef.myRefServer.domain.user.entity.User;
import com.myRef.myRefServer.domain.user.repository.UserRepository;
import com.myRef.myRefServer.service.user.UserServiceImpl;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.transaction.annotation.Transactional;


@SpringBootTest
@Transactional
@DisplayName("User Service Test")
public class UserServiceTest {
    private final String EMAIL = "eunji301@naver.com";
    private final String PWD = "qkrdmswl303!";
    private final String NICKNAME = "언지";

    @Autowired
    private UserServiceImpl userService;

    @Autowired
    private UserRepository userRepository;

    @Test
    public ResponseEntity<JwtTokenDto> logIn() throws Exception {
        // given
        UserLoginDto user = createLoginRequest();

        //then
        return userService.logIn(user);
    }

    @Test
    public void signUp() throws Exception {
        //given
        UserRegisterDto user = createSignupRequest();

        //then
        Long userID = userService.signUp(user);

        System.out.println(userID);
        System.out.println();
    }

    private UserRegisterDto createSignupRequest() {
        return UserRegisterDto.builder()
                .userEmail(EMAIL)
                .userPassword(PWD)
                .userNickname(NICKNAME)
                .build();
    }


    private UserLoginDto createLoginRequest() {
        return UserLoginDto.builder()
                .userEmail(EMAIL)
                .userPassword(PWD)
                .build();
    }
}
