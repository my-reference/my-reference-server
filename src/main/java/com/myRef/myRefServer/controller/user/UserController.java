package com.myRef.myRefServer.controller.user;

import com.myRef.myRefServer.domain.user.DTO.UserRegisterDto;
import com.myRef.myRefServer.service.user.UserServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import javax.validation.Valid;


@Controller
@RequiredArgsConstructor
@RequestMapping("/v1")
public class UserController {

    private final UserServiceImpl userService;

    @PostMapping("/signup")
    @ResponseStatus(HttpStatus.OK)
    public Long signUp(@Valid @RequestBody UserRegisterDto request) throws Exception {
        System.out.println(request.getUserEmail());
        return userService.signUp(request);
    }

}
