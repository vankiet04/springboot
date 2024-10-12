package net.enjoy.springboot.registrationlogin.service;

import net.enjoy.springboot.registrationlogin.dto.CategoryDto;
import net.enjoy.springboot.registrationlogin.dto.UserDto;
import net.enjoy.springboot.registrationlogin.entity.Category;
import net.enjoy.springboot.registrationlogin.entity.User;
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
        return categories.stream().map(this::convertEntityToDto).collect(Collectors.toList());
    }

    private CategoryDto convertEntityToDto(Category category) {
        return new CategoryDto(category.getId(), category.getCategoryName());
    }

    @Override
    public Category findById(Long id) {
        System.out.println("_____ TOI DA CHAY DEN DAY ****** service" + id);

        return categoryRepository.findById(id).orElse(null);
        // TODO Auto-generated method stub
    }

}
