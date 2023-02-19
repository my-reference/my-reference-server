package com.myRef.myRefServer.domain.category.entity;

import com.myRef.myRefServer.domain.user.entity.User;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "UserCategory")
@Entity
public class Category {

    @Id
    @Column(name = "categoryId")
    private Long categoryId;

    @ManyToOne(targetEntity = User.class, fetch = FetchType.LAZY    )
    @JoinColumn(name = "userEmail")
    private User userEmail;

    @Column(name = "categoryName")
    private String categoryName;

    @Column(name = "isFavorite")
    private boolean isFavorite;
}
