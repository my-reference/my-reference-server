package com.myRef.myRefServer.domain.category.DTO;

import com.myRef.myRefServer.domain.category.entity.Category;
import lombok.Builder;
import lombok.Data;

public class CategoryResDto {

    @Data
    @Builder
    public static class CategoryDto {
        private Long categoryId;
        private String categoryName;
        private boolean isFavorite;
    }
}
