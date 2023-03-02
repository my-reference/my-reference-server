package com.myRef.myRefServer.domain.category.DTO;

import com.myRef.myRefServer.domain.category.entity.Category;
import com.myRef.myRefServer.domain.user.entity.User;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

public class CategoryReqDto {

    @Data
    @NoArgsConstructor
    public static class CategoryAddDto {
        private String categoryName;

        @Builder
        public CategoryAddDto(String categoryName) {
            this.categoryName = categoryName;
        }

        public Category dtoToEntity(User id) {
            return Category.builder()
                    .user(id)
                    .categoryName(categoryName)
                    .build();
        }
    }

    @Data
    @NoArgsConstructor
    public static class CategoryDeleteDto {
        private Long categoryId;

        @Builder
        public CategoryDeleteDto(Long categoryId) {
            this.categoryId = categoryId;
        }
    }

    @Data
    @Builder
    public static class CategoryToFavoriteDto {
        private User id;
        private Long categoryId;
        private boolean isFavorite;
    }
}
