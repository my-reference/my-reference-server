package com.myRef.myRefServer.domain.category.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.myRef.myRefServer.domain.BaseTimeEntity;
import com.myRef.myRefServer.domain.user.entity.User;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.ColumnDefault;


@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "Category")
@Entity
public class Category extends BaseTimeEntity {
    @Id
    @Column(name = "CATEGORY_ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long categoryId;

    @ManyToOne(targetEntity = User.class, fetch = FetchType.LAZY)
    @JoinColumn(name = "USER_ID")
    @JsonIgnore
    private User user;

    @Column(name = "CATEGORY_NAME")
    private String categoryName;

    @Column(name = "IS_FAVORITE", columnDefinition = "TINYINT(1) DEFAULT 0")
    private boolean isFavorite;
}
