package net.enjoy.springboot.registrationlogin.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import net.enjoy.springboot.registrationlogin.dto.ProductDto;
import org.springframework.beans.factory.annotation.Autowired;
import net.enjoy.springboot.registrationlogin.service.ProductService;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import java.util.List;



@CrossOrigin(origins = "http://localhost:8080")
@RestController
@RequestMapping("/api/products")
public class API_ProductController {
    @Autowired
    private ProductService productService;

    @GetMapping("/getall") 
    public List<ProductDto> getAllProducts() {
        return productService.findAllProduct();
    }

    // get product with page param, for example /api/products/getproduct?page=1
    @GetMapping("/getProductPage")
    public List<ProductDto> getProductWithPage(@RequestParam(defaultValue = "1") int page) {
        return productService.findAllProductWithPage(page);
    }

}
