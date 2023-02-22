package com.myRef.myRefServer.domain.category.entity;

import com.myRef.myRefServer.domain.user.entity.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "Category")
@Entity
public class Category {
    @Id
    @Column(name = "categoryId")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long categoryId;

    @ManyToOne(targetEntity = User.class, fetch = FetchType.LAZY)
    @JoinColumn(name = "userId")
    private User id;

    @Column(name = "categoryName")
    private String categoryName;

    @Column(name = "isFavorite")
    private boolean isFavorite;
}
