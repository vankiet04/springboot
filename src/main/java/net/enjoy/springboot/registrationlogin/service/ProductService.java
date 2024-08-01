package net.enjoy.springboot.registrationlogin.service;

import net.enjoy.springboot.registrationlogin.dto.ProductDto;
import net.enjoy.springboot.registrationlogin.entity.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ProductService {
    Page<ProductDto> findAllProduct(Pageable pageable);

    Product findById(Long id);

    void saveProduct(Product product);

    public Page<ProductDto> searchProduct(Long sizeId, Long colorId, Long categoryId, long minPrice, long maxPrice, Pageable pageable, String name);
}
