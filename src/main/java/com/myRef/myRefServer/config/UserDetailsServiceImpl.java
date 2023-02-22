package com.myRef.myRefServer.config;

import com.myRef.myRefServer.controller.exception.UserNotFoundException;
import com.myRef.myRefServer.domain.user.entity.User;
import com.myRef.myRefServer.domain.user.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
public class UserDetailsServiceImpl implements UserDetailsService { // UserDetailsService: Spring Security에서 유저의 정보를 가져오는 인터페이스

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String userEmail) throws UsernameNotFoundException {
        System.out.println("email in loadUserByUsername = " + userEmail);
        User user = userRepository.findByUserEmail(userEmail)
                .orElseThrow(UserNotFoundException::new);
        Set<GrantedAuthority> grantedAuthorities = new HashSet<>();

        return new org
                .springframework
                .security
                .core
                .userdetails
                .User(user.getUserEmail(), user.getUserPassword(), grantedAuthorities);
    }

}
