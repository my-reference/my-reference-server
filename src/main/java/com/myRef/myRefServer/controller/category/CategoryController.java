package com.myRef.myRefServer.controller.category;

import com.myRef.myRefServer.controller.exception.UserNotFoundException;
import com.myRef.myRefServer.domain.category.DTO.CategoryReqDto.*;
import com.myRef.myRefServer.domain.category.entity.Category;
import com.myRef.myRefServer.domain.user.entity.User;
import com.myRef.myRefServer.service.category.CategoryServiceImpl;
import com.myRef.myRefServer.service.user.UserServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/v1/category")
public class CategoryController {

    private final UserServiceImpl userService;
    private final CategoryServiceImpl categoryService;

    @PostMapping("/new")
    public Long addCategory(@RequestBody CategoryAddDto categoryAddDto, @AuthenticationPrincipal UserDetails userDetails) throws UserNotFoundException {
        User userDetail = userService.findByEmail(userDetails.getUsername())
                .orElseThrow(() -> new UserNotFoundException());

        return categoryService.addCategory(categoryAddDto, userDetail);
    }

    @DeleteMapping("/delete")
    public void deleteCategory(@RequestBody CategoryDeleteDto categoryDeleteDto) throws UserNotFoundException {
        try{
            categoryService.deleteCategory(categoryDeleteDto);
            // "OK"로 response 보내기 추가해야함
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

    }


    @GetMapping("/list")
    public List<Category> getCategoryList(@AuthenticationPrincipal UserDetails userDetails) {
        User userDetail = userService.findByEmail(userDetails.getUsername())
                        .orElseThrow(() -> new UserNotFoundException());

        return categoryService.getCategoryList(userDetail);
    }

//    @PatchMapping("/update")
//    public ResponseEntity<CategoryDto> updateCategory() {
//        return;
//    }
}
