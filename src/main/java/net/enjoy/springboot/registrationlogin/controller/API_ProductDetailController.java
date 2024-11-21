package net.enjoy.springboot.registrationlogin.controller;

import net.enjoy.springboot.registrationlogin.dto.ProductDetailDto;
import net.enjoy.springboot.registrationlogin.service.ProductDetailService;

import java.lang.reflect.Array;
import java.util.ArrayList;

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
       ArrayList<ProductDetailDto> productDetailDtos = productDetailService.findListProductDetailByProductId(productId);
         return new ResponseEntity(productDetailDtos, HttpStatus.OK);

    }
}