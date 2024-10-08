package net.enjoy.springboot.registrationlogin.controller;

import net.enjoy.springboot.registrationlogin.dto.ProductDto;
import net.enjoy.springboot.registrationlogin.entity.Product;
import net.enjoy.springboot.registrationlogin.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/products")
@CrossOrigin(origins = "http://localhost:3000")
public class API_ProductController {
    @Autowired
    private ProductService productService;

    @GetMapping
    public List<ProductDto> getAllProducts() {
        return productService.getAllProducts();
    }
     @PutMapping("/update-status/{id}")
    public void updateProductStatus(@PathVariable Long id) {
        productService.updateProductStatus(id, 0);
    }
    
}