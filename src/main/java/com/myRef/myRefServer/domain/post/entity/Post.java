package com.myRef.myRefServer.domain.post.entity;

import com.myRef.myRefServer.domain.category.entity.Category;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "UserPost")
@Entity
public class Post {
    @Id
    @Column(name = "postId")
    private Long postId;

    @ManyToOne(targetEntity = Category.class, fetch = FetchType.LAZY)
    @JoinColumn(name = "categoryName")
    private Category categoryName;

    @Column(name = "postLink")
    private String postLink;
}
