package com.myRef.myRefServer.service.user;

import com.myRef.myRefServer.config.jwt.JwtTokenDto;
import com.myRef.myRefServer.config.jwt.JwtTokenProvider;
import com.myRef.myRefServer.config.jwt.RegenarateTokenDto;
import com.myRef.myRefServer.domain.user.DTO.UserLoginDto;
import com.myRef.myRefServer.domain.user.DTO.UserRegisterDto;
import com.myRef.myRefServer.domain.user.entity.User;
import com.myRef.myRefServer.domain.user.repository.UserRepository;
import com.myRef.myRefServer.exception.CustomException;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;
import java.util.concurrent.TimeUnit;


@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    private final AuthenticationManager authenticationManager;
    private final JwtTokenProvider jwtTokenProvider;
    private final RedisTemplate<String, String> redisTemplate;

    @Value("${jwt.refresh_token.expiration}")
    private long refresh_token_expire_time;

    @Value("${jwt.access_token.expiration}")
    private long access_token_expire_time;

    @Override
    @Transactional
    public Long signUp(UserRegisterDto requestDto) throws Exception {

        if (userRepository.existsByUserEmail(requestDto.getUserEmail())) {
            throw new Exception("?????? ???????????? ??????????????????.");
        }

        User user = userRepository.save(requestDto.dtoToEntity().hashPassword(bCryptPasswordEncoder));
        System.out.println(user.getUserPassword());
        return user.getId();
    }


    @Override
    public ResponseEntity<JwtTokenDto> logIn(UserLoginDto requestDto) {
        try {
            System.out.println("inside try");
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            requestDto.getUserEmail(),
                            requestDto.getUserPassword()
                    )
            );

            System.out.println("refresh token");
            System.out.println(authentication);
            String refreshToken = jwtTokenProvider.generateRefreshToken(authentication);

            System.out.println("success");
            JwtTokenDto jwtTokenDto = new JwtTokenDto(jwtTokenProvider.generateAccessToken(authentication), refreshToken, access_token_expire_time);
            System.out.println(jwtTokenDto);

            //Redis??? ?????? - ?????? ?????? ????????? ?????? ?????? ?????? ??????
            redisTemplate.opsForValue().set(authentication.getName(), refreshToken, refresh_token_expire_time, TimeUnit.MILLISECONDS);

            HttpHeaders httpHeaders = new HttpHeaders();
            httpHeaders.add("Authorization", "Bearer " + jwtTokenDto.getAccess_token());

            return new ResponseEntity<>(jwtTokenDto, httpHeaders, HttpStatus.OK);
        } catch (AuthenticationException e) {
            throw new CustomException("????????? ????????????.", HttpStatus.BAD_REQUEST);
        }
    }

    @Override
    public ResponseEntity<JwtTokenDto> regenerateToken(RegenarateTokenDto refreshTokenDto) {
        String refreshToken = refreshTokenDto.getRefresh_token();
        try {
            // Refresh Token ??????
            if (!jwtTokenProvider.validateRefreshToken(refreshToken)) {
                throw new CustomException("???????????? ?????? Refresh ???????????????.", HttpStatus.BAD_REQUEST);
            }

            // Access Token?????? User email??? ????????????.
            Authentication authentication = jwtTokenProvider.getAuthenticationByRefreshToken(refreshToken);

            // Redis??? ????????? Refresh Token ?????? ????????????.
            String savedRefreshToken = redisTemplate.opsForValue().get(authentication.getName());
            if (!savedRefreshToken.equals(refreshToken)) {
                throw new CustomException("Refresh Token??? ???????????? ????????????.", HttpStatus.BAD_REQUEST);
            }

            // ?????? ?????????
            String newRefreshToken = jwtTokenProvider.generateRefreshToken(authentication);
            JwtTokenDto jwtTokenDto = new JwtTokenDto(jwtTokenProvider.generateAccessToken(authentication), newRefreshToken, refresh_token_expire_time);

            // ???????????? Refresh Token??? Redis??? ????????????
            redisTemplate.opsForValue().set(authentication.getName(), newRefreshToken, refresh_token_expire_time, TimeUnit.MILLISECONDS);

            HttpHeaders httpHeaders = new HttpHeaders();

            return new ResponseEntity<>(jwtTokenDto, httpHeaders, HttpStatus.OK);
        } catch (AuthenticationException e) {
            throw new CustomException("???????????? ?????? Refresh ???????????????.", HttpStatus.BAD_REQUEST);
        }
    }

    @Override
    public Optional<User> findByEmail(String email) {
        System.out.println("found it");
        return userRepository.findByUserEmail(email);
    }

}
