package com.myRef.myRefServer.service.category;

import com.myRef.myRefServer.domain.category.DTO.CategoryReqDto.*;
import com.myRef.myRefServer.domain.category.entity.Category;
import com.myRef.myRefServer.domain.user.entity.User;

import java.util.List;

public interface CategoryService {
    Long addCategory(CategoryAddDto requestDto, User user);
    List<Category> getCategoryList(User user);
    Long addFavoriteCategory();
    void deleteCategory(CategoryDeleteDto requestDto);
}
