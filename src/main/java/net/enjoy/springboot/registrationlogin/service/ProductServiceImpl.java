package net.enjoy.springboot.registrationlogin.service;

import net.enjoy.springboot.registrationlogin.dto.ProductDto;
import net.enjoy.springboot.registrationlogin.dto.UserDto;
import net.enjoy.springboot.registrationlogin.entity.Product;
import net.enjoy.springboot.registrationlogin.entity.User;
import net.enjoy.springboot.registrationlogin.repository.ProductRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;
@Service
public class ProductServiceImpl implements ProductService {
    ProductRepository productRepository;
    public ProductServiceImpl(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public Page<ProductDto> findAllProduct(Pageable pageable) {
        Page<Product> products = productRepository.findAll(pageable);
        List<ProductDto> productDtos = products.stream()
                .map(this::convertEntityToDto)
                .collect(Collectors.toList());
        return new PageImpl<>(productDtos, pageable, products.getTotalElements());
    }

    private ProductDto convertEntityToDto(Product product) {
        return new ProductDto(
                product.getId(),
                product.getName(),
                product.getImage(),
                product.getDescription(),
                product.getPrice(),
                product.getQuantity(),
                product.getCategory(),
                product.isStatus()
        );
    }

}
