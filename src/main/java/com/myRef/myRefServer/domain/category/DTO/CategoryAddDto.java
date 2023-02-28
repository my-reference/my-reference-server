package com.myRef.myRefServer.domain.category.DTO;

import com.myRef.myRefServer.domain.category.entity.Category;
import com.myRef.myRefServer.domain.user.entity.User;
import jakarta.validation.constraints.NotEmpty;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Data
@Getter
@NoArgsConstructor
public class CategoryAddDto {

    @NotEmpty(message = "카테고리 이름을 입력해주세요.")
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
