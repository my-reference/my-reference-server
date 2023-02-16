package com.ksone.myreferenceserver.domain.userPosts;

import com.ksone.myreferenceserver.domain.userCategory.UserCategory;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "UserPosts")
@Entity
public class UserPosts {
    @Id
    @Column(name = "postId")
    private Long postId;

    @ManyToOne(targetEntity = UserCategory.class, fetch = FetchType.LAZY)
    @JoinColumn(name = "categoryName")
    private UserCategory categoryName;

    @Column(name = "postLink")
    private String postLink;
}
