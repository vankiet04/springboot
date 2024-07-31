package net.enjoy.springboot.registrationlogin.controller;

import net.enjoy.springboot.registrationlogin.dto.ProductDto;
import net.enjoy.springboot.registrationlogin.service.CategoryService;
import net.enjoy.springboot.registrationlogin.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.stream.IntStream;

@Controller
public class ProductController {
    private final ProductService productService;
    private final CategoryService categoryService;

    @Autowired
    public ProductController(ProductService productService, CategoryService categoryService) {
        this.productService = productService;
        this.categoryService = categoryService;
    }
    @GetMapping("/index")
    public String index(Model model, @RequestParam(defaultValue = "0") int page) {
        Pageable pageable = PageRequest.of(page, 2); // 2 sản phẩm mỗi trang
        Page<ProductDto> productPage = productService.findAllProduct(pageable);
        model.addAttribute("productPage", productPage);
        model.addAttribute("pageNumbers", IntStream.range(0, productPage.getTotalPages()).toArray());
        return "index";
    }
    public String getCategoryNameById(int categoryId) {
        return categoryService.findCategoryNameById(categoryId);
    }



}
