package com.ksone.myreferenceserver.domain.userCategory;

import com.ksone.myreferenceserver.domain.user.User;
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
public class UserCategory {

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
