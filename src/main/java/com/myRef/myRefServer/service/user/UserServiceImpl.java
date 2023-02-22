package com.myRef.myRefServer.service.user;

import com.myRef.myRefServer.config.JwtTokenDto;
import com.myRef.myRefServer.config.JwtTokenProvider;
import com.myRef.myRefServer.domain.user.DTO.UserLoginDto;
import com.myRef.myRefServer.domain.user.DTO.UserRegisterDto;
import com.myRef.myRefServer.domain.user.entity.User;
import com.myRef.myRefServer.domain.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder bCryptPasswordEncoder;
    private final AuthenticationManager authenticationManager;
    private final JwtTokenProvider jwtTokenProvider;

    @Override
    public Long signUp(UserRegisterDto requestDto) throws Exception {

        if (userRepository.existsByUserEmail(requestDto.getUserEmail())) {
            throw new Exception("이미 존재하는 이메일입니다.");
        }

        User user = userRepository.save(requestDto.dtoToEntity());
        user.hashPassword(bCryptPasswordEncoder);
        System.out.println(user.getId());
        return user.getId();
    }
//
//    @Override
//    public ResponseEntity<UserLoginDto> logIn(UserLoginDto requestDto) {
//        try {
//            Authentication authentication = authenticationManager.authenticate(
//                    new UsernamePasswordAuthenticationToken(
//                            requestDto.getUserEmail(),
//                            requestDto.getUserPassword()
//                    )
//            );
//
//            JwtTokenDto jwtTokenDto = new JwtTokenDto(jwtTokenProvider.generateToken(authentication));
//        }
//
//        return new ResponseEntity<>()
//    }

}
