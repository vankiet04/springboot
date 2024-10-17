    package net.enjoy.springboot.registrationlogin.controller;

    import net.enjoy.springboot.registrationlogin.dto.CategoryDto;
import net.enjoy.springboot.registrationlogin.entity.Category;
import net.enjoy.springboot.registrationlogin.service.CategoryService;
    import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:8080")
@RestController
@RequestMapping("/api/categories")
public class API_CategoryController {

    @Autowired
    private CategoryService categoryService;

    @GetMapping("/getall")
    public List<CategoryDto> getAllCategories() {
        return categoryService.findAllCategory();
    }

    @PostMapping("/add")
    public CategoryDto addCategory(@RequestBody CategoryDto categoryDto) {
        categoryDto.setStatus(1);
        return categoryService.saveCategoryAPI(categoryDto);
    }

    @PutMapping("/update/{id}")
    public Category updateCategory(@PathVariable Long id, @RequestBody CategoryDto categoryDto) {
        System.out.println("ID tôi đang sửa thể loại _____: " + id);    
     Category category = categoryService.findById(id);
        category.setCategoryName(categoryDto.getName());
        return categoryService.updateCategoryAPI(category);
    }

    @PutMapping("/updateStatus/{id}")
    public Category updateStatus(@PathVariable Long id) {
        // Trạng thái thể loại 0 là đang bị ẩn, 1 là đang hiển thị
        Category category = categoryService.findById(id);
        if (category.getStatus() == 0) {
            category.setStatus(1);
        } else {
            category.setStatus(0);
        }
        return categoryService.updateCategoryAPI(category);
    }
}