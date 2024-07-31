package net.enjoy.springboot.registrationlogin.service;


import net.enjoy.springboot.registrationlogin.dto.ProductDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ProductService {
    Page<ProductDto> findAllProduct(Pageable pageable);
}
