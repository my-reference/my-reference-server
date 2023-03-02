package com.myRef.myRefServer.service.category;

import com.myRef.myRefServer.domain.category.DTO.CategoryReqDto.*;
import com.myRef.myRefServer.domain.category.entity.Category;
import com.myRef.myRefServer.domain.category.repository.CategoryRepository;
import com.myRef.myRefServer.domain.user.entity.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService{

    private final CategoryRepository categoryRepository;

    @Override
    public Long addCategory(CategoryAddDto requestDto, User user) {
        Category category = categoryRepository.save(requestDto.dtoToEntity(user));
        return category.getCategoryId();
    }

    @Override
    public List<Category> getCategoryList(User user) {
        System.out.println(user.getId());
        return categoryRepository.findByUser_Id(user.getId());
    }

    @Override
    public Long addFavoriteCategory() {
        return null;
    }

    @Override
    public void deleteCategory(CategoryDeleteDto requestDto) {
        categoryRepository.deleteById(requestDto.getCategoryId());
    }
}
