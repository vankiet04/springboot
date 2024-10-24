package net.enjoy.springboot.registrationlogin.service;

import net.enjoy.springboot.registrationlogin.dto.CategoryDto;
import net.enjoy.springboot.registrationlogin.entity.Category;

import java.util.List;

public interface CategoryService {
    List<CategoryDto> findAllCategory();

    Category findById(Long id);
   
    //API
    CategoryDto findCategoryDtoById(Long categoryId);
    CategoryDto saveCategoryAPI(CategoryDto categoryDto);
    Category updateCategoryAPI(Category category);
}
