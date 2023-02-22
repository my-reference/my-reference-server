package com.myRef.myRefServer.domain.post.entity;

import com.myRef.myRefServer.domain.category.entity.Category;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "Post")
@Entity
public class Post {
    @Id
    @Column(name="postId")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long postId;

    @ManyToOne(targetEntity = Category.class, fetch = FetchType.LAZY)
    @JoinColumn(name = "categoryId")
    private Category categoryId;

    @Column(name = "postLink")
    private String postLink;
}
