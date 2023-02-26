package com.myRef.myRefServer.domain.category.DTO;

import com.myRef.myRefServer.domain.category.entity.Category;
import com.myRef.myRefServer.domain.user.entity.User;
import lombok.Builder;
import lombok.Data;

public class CategoryReqDto {

    @Data
    @Builder
    public static class CategoryAddDto {
        private String categoryName;
    }

    @Data
    @Builder
    public static class CategoryToFavoriteDto {
        private User id;
        private Long categoryId;
        private boolean isFavorite;
    }
}
