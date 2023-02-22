package com.myRef.myRefServer.controller.user;

import com.myRef.myRefServer.domain.user.DTO.UserRegisterDto;
import com.myRef.myRefServer.service.user.UserServiceImpl;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;


import static com.mysql.cj.conf.PropertyKey.logger;


@RestController
@RequiredArgsConstructor
@RequestMapping( "/v1")
public class UserController {

    private final UserServiceImpl userService;

    @GetMapping(value = "/signup")
    public boolean get() {
        return true;
    }

    @PostMapping(value = "/signup")
    public Long signUp(@Valid @RequestBody UserRegisterDto request) throws Exception {
        System.out.println("안타??");
        return userService.signUp(request);
    }

}
