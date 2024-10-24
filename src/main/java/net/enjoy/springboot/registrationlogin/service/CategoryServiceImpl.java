package net.enjoy.springboot.registrationlogin.service;

import net.enjoy.springboot.registrationlogin.dto.CategoryDto;
import net.enjoy.springboot.registrationlogin.entity.Category;
import net.enjoy.springboot.registrationlogin.repository.CategoryRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;
@Service
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    private  CategoryRepository categoryRepository;

    public CategoryServiceImpl(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @Override   
    public List<CategoryDto> findAllCategory() {
        List<Category> categories = categoryRepository.findAll();
        // chi co category co status = 1 moi duoc hien thi
        categories = categories.stream().filter(category -> category.getStatus() == 1).collect(Collectors.toList());
        return categories.stream().map(this::convertEntityToDto).collect(Collectors.toList());
    }

    private CategoryDto convertEntityToDto(Category category) {
        return new CategoryDto(category.getId(), category.getCategoryName(), category.getStatus());
    }

    @Override
    public Category findById(Long id) {
        System.out.println("_____ TOI DA CHAY DEN DAY ****** service" + id);

        return categoryRepository.findById(id).orElse(null);
        // TODO Auto-generated method stub
    }

    @Override
    public CategoryDto saveCategoryAPI(CategoryDto categoryDto) {
        Category category = new Category();
        category.setCategoryName(categoryDto.getCategoryName());
        category.setStatus(categoryDto.getStatus());
        categoryRepository.save(category);
        return categoryDto;
    }
    @Override
    public CategoryDto findCategoryDtoById(Long categoryId) {
        Category category = categoryRepository.findById(categoryId).orElse(null);
        return new CategoryDto(category.getId(), category.getCategoryName(), category.getStatus());
    }

    @Override
    public Category updateCategoryAPI(Category category) {
        categoryRepository.save(category);
        return category;
    }
}
