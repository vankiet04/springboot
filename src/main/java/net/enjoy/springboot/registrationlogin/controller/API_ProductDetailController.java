package net.enjoy.springboot.registrationlogin.controller;

import net.enjoy.springboot.registrationlogin.dto.ProductDetailDto;
import net.enjoy.springboot.registrationlogin.service.ProductDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "http://localhost:8080")
@RestController
@RequestMapping("/api/products")
public class API_ProductDetailController {

    @Autowired
    private ProductDetailService productDetailService;

    @GetMapping("/{productId}/details")
    public ResponseEntity<ProductDetailDto> getProductDetails(@PathVariable Long productId) {
        ProductDetailDto productDetail = productDetailService.findProductDetailByProductId(productId);
        if (productDetail != null) {
            return new ResponseEntity<>(productDetail, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}