package com.myRef.myRefServer.UserTest;

import com.myRef.myRefServer.domain.user.DTO.UserRegisterDto;
import com.myRef.myRefServer.domain.user.entity.User;
import com.myRef.myRefServer.domain.user.repository.UserRepository;
import com.myRef.myRefServer.service.user.UserServiceImpl;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.transaction.annotation.Transactional;


@SpringBootTest
@Transactional
@DisplayName("User Service Test")
public class UserServiceTest {
    private final String EMAIL = "qkrdmswl303@gmail.com";
    private final String PWD = "qkrdmswl303!";
    private final String NICKNAME = "언지";

    @Autowired
    private UserServiceImpl userService;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder bCryptPasswordEncoder;

    @Test
    public void signUp() throws Exception {
        //given
        UserRegisterDto user = createSignupRequest();

        //when
        Long userID = userService.signUp(user);

        //then
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
}
