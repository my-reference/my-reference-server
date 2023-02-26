package com.myRef.myRefServer.controller.category;

import com.myRef.myRefServer.controller.exception.UserNotFoundException;
import com.myRef.myRefServer.domain.category.DTO.CategoryResDto.*;
import com.myRef.myRefServer.domain.category.DTO.CategoryReqDto.*;
import com.myRef.myRefServer.domain.category.DTO.CategoryAddDto;
import com.myRef.myRefServer.domain.user.entity.User;
import com.myRef.myRefServer.service.category.CategoryServiceImpl;
import com.myRef.myRefServer.service.user.UserServiceImpl;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/v1/category")
public class CategoryController {

    private final UserServiceImpl userService;
    private final CategoryServiceImpl categoryService;

    @PostMapping("/new")
    public CategoryAddDto addCategory(@RequestBody CategoryAddDto categoryAddDto) throws UserNotFoundException {
        System.out.println("1111");
//        User userDetail = userService.findByEmail(userDetails.getUsername())
//                .orElseThrow(() -> new UserNotFoundException());

//        System.out.println(userDetail);
        System.out.println(categoryAddDto);
        return categoryAddDto;
//        return categoryService.addCategory(categoryAddDto, userDetail);
    }

    @DeleteMapping("/delete")
    public void deleteCategory() {

    }

//    @PatchMapping("/update")
//    public ResponseEntity<CategoryDto> updateCategory() {
//        return;
//    }
}
