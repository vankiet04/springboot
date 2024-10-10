package net.enjoy.springboot.registrationlogin.service;

import net.enjoy.springboot.registrationlogin.dto.CategoryDto;
import org.springframework.stereotype.Service;
import net.enjoy.springboot.registrationlogin.entity.Category;

import java.util.List;


public interface CategoryService {
//    List<CategoryDto> findAllCategory();
//    String findCategoryNameById(Long categoryId);
    // get all categories
    List<CategoryDto> findAllCategory();
   Category findCategoryById(Long id);
    
}
