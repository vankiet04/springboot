package net.enjoy.springboot.registrationlogin.service;

import net.enjoy.springboot.registrationlogin.dto.ProductDto;
import net.enjoy.springboot.registrationlogin.entity.Product;
import net.enjoy.springboot.registrationlogin.repository.ProductsRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProductServiceImpl implements ProductService {

    ProductsRepository productsRepository;

    public ProductServiceImpl(ProductsRepository productsRepository) {
        this.productsRepository = productsRepository;
    }

    @Override
    public Page<ProductDto> findAllProduct(Pageable pageable) {
        Page<Product> products = productsRepository.findAll(pageable);
        return products.map(this::convertEntityToDto);
    }

    // findProduct base on size, colo
    @Override
    public Page<ProductDto> searchProduct(Long sizeId, Long colorId, Long categoryId, long minPrice, long maxPrice, Pageable pageable, String name) {
        Page<Product> products = productsRepository.findbyFilter(sizeId, colorId, categoryId, minPrice, maxPrice, pageable, name);
        return products.map(this::convertEntityToDto);
    }

    @Override
    public Product findById(Long id) {
        return null;
    }

    @Override
    public void saveProduct(Product product) {

    }

    private ProductDto convertEntityToDto(Product product) {
        ProductDto productDto = new ProductDto();
        productDto.setId(product.getId());
        productDto.setName(product.getName());
        productDto.setDescription(product.getDescription());
        productDto.setImg(product.getImg());
        return productDto;
    }
}
