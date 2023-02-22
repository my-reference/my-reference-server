package com.myRef.myRefServer.domain.user.entity;

import com.myRef.myRefServer.domain.BaseTimeEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.persistence.*;
import javax.validation.constraints.Email;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "User")
@Entity
public class User extends BaseTimeEntity {
    @Id
    @Column(name="userId")
    @GeneratedValue(strategy = GenerationType.IDENTITY)  // Identity : auto-increment
    private Long id;

    @Email
    @Column(nullable = false, unique = true)
    private String userEmail;

    @Column(nullable = false, length = 100)
    private String userPassword;

    @Column(nullable = false)
    private String userNickname;

    public User hashPassword(PasswordEncoder passwordEncoder) {
        this.userPassword = passwordEncoder.encode(this.userPassword);
        return this;
    }
}
