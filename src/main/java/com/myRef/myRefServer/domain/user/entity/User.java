package com.myRef.myRefServer.domain.user.entity;

import com.myRef.myRefServer.domain.BaseTimeEntity;
import com.myRef.myRefServer.domain.category.entity.Category;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.ArrayList;
import java.util.List;


@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "User")
@Entity
public class User extends BaseTimeEntity {
    @Id
    @Column(name = "userId")
    @GeneratedValue(strategy = GenerationType.IDENTITY)  // Identity : auto-increment
    private Long id;

    @Email
    @Column(nullable = false, unique = true)
    private String userEmail;

    @Column(nullable = false, length = 100)
    private String userPassword;

    @Column(nullable = false)
    private String userNickname;

    @OneToMany(mappedBy = "user")
    List<Category> categoryList = new ArrayList<>();

    public User hashPassword(BCryptPasswordEncoder passwordEncoder) {
        this.userPassword = passwordEncoder.encode(this.userPassword);
        return this;
    }
}
