package net.enjoy.springboot.registrationlogin.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import net.enjoy.springboot.registrationlogin.dto.ProductDto;
import net.enjoy.springboot.registrationlogin.entity.Category;
import net.enjoy.springboot.registrationlogin.entity.Product;

import org.h2.expression.function.SysInfoFunction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import net.enjoy.springboot.registrationlogin.service.CategoryService;
import net.enjoy.springboot.registrationlogin.service.ProductService;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;
//import category



@CrossOrigin(origins = "http://localhost:8080")
@RestController
@RequestMapping("/api/products")
public class API_ProductController {
    @Autowired
    private ProductService productService;
    private CategoryService categoryService;
    @GetMapping("/getall") 
    public List<ProductDto> getAllProducts() {
        return productService.findAllProduct();
    }

    // get product with page param, for example /api/products/getproduct?page=1
    @GetMapping("/getProductPage")
    public List<ProductDto> getProductWithPage(@RequestParam(defaultValue = "1") int page) {
        return productService.findAllProductWithPage(page);
    }


    @PostMapping("/add")
    public ResponseEntity<HttpStatus> addNewProduct(@RequestBody Product product) {
        System.out.println("__________TOI VAO DAY" + product.getName());
        System.out.println("__________TOI VAO DAY" + product.getImg());
        System.out.println("__________TOI VAO DAY" + product.getDescription());
        System.out.println("__________TOI VAO DAY" + product.getId());
        System.out.println("__________TOI VAO DAY" + product.getStatus());
        System.out.println("__________TOI VAO DAY" + product.getCategory());

        Category category = new Category();
        category.setId(1L);
        category.setCategoryName("category 1");
        product.setCategory(category);
        System.out.println("__________TOI VAO DAY" + product.getCategory());
        try{
            productService.addProductAPI(product);
            System.out.println("__________TOI THEM THANH CONG");
            return new ResponseEntity<>(HttpStatus.OK);
        }
        catch (Exception e) {
            System.out.println("_______TOI DANG LOI: "+e);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    

}
