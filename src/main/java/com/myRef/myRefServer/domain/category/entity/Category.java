package com.myRef.myRefServer.domain.category.entity;

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
    @Column(name = "categoryId")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long categoryId;

    @ManyToOne(targetEntity = User.class, fetch = FetchType.LAZY)
    @JoinColumn(name = "userId")
    private User id;

    @Column(name = "categoryName")
    private String categoryName;

    @Column(name = "isFavorite", columnDefinition = "TINYINT(1) DEFAULT 0")
    private boolean isFavorite;
}
