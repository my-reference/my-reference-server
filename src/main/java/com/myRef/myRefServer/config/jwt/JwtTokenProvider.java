package com.myRef.myRefServer.config.jwt;

import com.myRef.myRefServer.exception.CustomException;
import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.text.DateFormat;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.Date;

/**
 * JwtTokenProvider: JWT 토큰 발급/인증 기능
 */

@Component
@RequiredArgsConstructor
public class JwtTokenProvider {

    @Value("${jwt.access_token.secret_key}")
    private String access_secret_key;

    @Value("${jwt.access_token.expiration}")
    private long access_expire_time;

    @Value("${jwt.refresh_token.secret_key}")
    private String refresh_secret_key;

    @Value("${jwt.refresh_token.expiration}")
    private long refresh_expire_time;

    private final UserDetailsService userDetailsService;


    /**
     * 적절한 설정을 통해 토큰을 생성하여 반환
     * @param authentication
     * @return
     */
    public String generateAccessToken(Authentication authentication) {
        Key key = Keys.hmacShaKeyFor(access_secret_key.getBytes(StandardCharsets.UTF_8));
        Claims claims = Jwts.claims().setSubject(authentication.getName());

        Date now = new Date();
        Date expiresIn = new Date(now.getTime() + access_expire_time);

        return Jwts.builder()
                .setClaims(claims)
                .setIssuedAt(now)
                .setExpiration(expiresIn)
                .signWith(key, SignatureAlgorithm.HS256)
                .compact();
    }


    /**
     * 적절한 설정을 통해 Refresh 토큰을 생성하여 반환
     * @param authentication
     * @return refresh token
     */
    public String generateRefreshToken(Authentication authentication) {
        Claims claims = Jwts.claims().setSubject(authentication.getName());
        Key key = Keys.hmacShaKeyFor(refresh_secret_key.getBytes(StandardCharsets.UTF_8));

        Instant issuedAt = Instant.now().truncatedTo(ChronoUnit.SECONDS);
        Instant expiration = issuedAt.plus(refresh_expire_time, ChronoUnit.SECONDS);

        return Jwts.builder()
                .setClaims(claims)
                .setIssuedAt(Date.from(issuedAt))
                .setExpiration(Date.from(expiration))
                .signWith(key, SignatureAlgorithm.HS256)
                .compact();
    }


    /**
     * Access 토큰으로부터 클레임을 만들고, 이를 통해 User 객체를 생성하여 Authentication 객체를 반환
     * @param accessToken
     * @return
     */
    public Authentication getAuthenticationByAccessToken(String accessToken) {
        String userPrincipal = Jwts.parserBuilder().setSigningKey(access_secret_key.getBytes()).build().parseClaimsJws(accessToken).getBody().getSubject();
        UserDetails userDetails = userDetailsService.loadUserByUsername(userPrincipal);

        return new UsernamePasswordAuthenticationToken(userDetails, "", userDetails.getAuthorities());
    }


    /**
     * Refresh 토큰으로부터 클레임을 만들고, 이를 통해 User 객체를 생성하여 Authentication 객체를 반환
     * @param refreshToken
     * @return
     */
    public Authentication getAuthenticationByRefreshToken(String refreshToken) {
        String userPrincipal = Jwts.parserBuilder().setSigningKey(refresh_secret_key.getBytes()).build().parseClaimsJws(refreshToken).getBody().getSubject();
        UserDetails userDetails = userDetailsService.loadUserByUsername(userPrincipal);

        return new UsernamePasswordAuthenticationToken(userDetails, "", userDetails.getAuthorities());
    }


    /**
     * http 헤더로부터 bearer 토큰을 가져옴.
     * @param req
     * @return
     */
    public String resolveToken(HttpServletRequest req) {
        String bearerToken = req.getHeader("Authorization");
        if (bearerToken != null && bearerToken.startsWith("Bearer ")) {
            return bearerToken.substring(7);
        }
        return null;
    }

    /**
     * Access 토큰을 검증
     * @param accessToken
     * @return
     */
    public boolean validateAccessToken(String accessToken) {
        try {
            Jwts.parserBuilder().setSigningKey(access_secret_key.getBytes()).build().parseClaimsJws(accessToken);
            return true;
        } catch (Exception e) {
            // MalformedJwtException | ExpiredJwtException | IllegalArgumentException
            throw new CustomException("Access 토큰이 유효하지 않습니다.", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    /**
     * Refresh 토큰을 검증
     * @param refreshToken
     * @return
     */
    public boolean validateRefreshToken(String refreshToken) {
        try {
            Jwts.parserBuilder().setSigningKey(refresh_secret_key.getBytes()).build().parseClaimsJws(refreshToken);
            return true;
        } catch (JwtException e) {
            // MalformedJwtException | ExpiredJwtException | IllegalArgumentException
            throw new CustomException("Refresh 토큰이 유효하지 않습니다.", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
