package com.myRef.myRefServer.domain.category.DTO;

import com.myRef.myRefServer.domain.category.entity.Category;
import com.myRef.myRefServer.domain.user.entity.User;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

public class CategoryReqDto {

    @Data
    public static class CategoryAddDto {
        private String categoryName;

        @Builder
        public CategoryAddDto(String categoryName) {
            this.categoryName = categoryName;
        }

        public Category dtoToEntity(User id) {
            return Category.builder()
                    .id(id)
                    .categoryName(categoryName)
                    .build();
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
