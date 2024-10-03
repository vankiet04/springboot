package net.enjoy.springboot.registrationlogin.service;

import net.enjoy.springboot.registrationlogin.dto.ProductDetailDto;
import net.enjoy.springboot.registrationlogin.dto.ProductDto;
import net.enjoy.springboot.registrationlogin.entity.Product;
import net.enjoy.springboot.registrationlogin.entity.ProductDetail;
import net.enjoy.springboot.registrationlogin.repository.ProductsDetailRespository;
import net.enjoy.springboot.registrationlogin.repository.ProductsRepository;
import service.collector.exception.ProductNotFoundException;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProductServiceImpl implements ProductService {

    ProductsRepository productsRepository;
    ProductsDetailRespository productsDetailRespository;

    public ProductServiceImpl(ProductsRepository productsRepository, ProductsDetailRespository productsDetailRespository) {
        this.productsRepository = productsRepository;
        this.productsDetailRespository = productsDetailRespository;
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
    public ProductDto findById(Long id) {
        Product product = productsRepository.findById(id);
        return convertEntityToDto(product);
    }

    @Override
    public void saveProduct(Product product) {

    }

    @Override
    public List<ProductDetailDto> findDetailById(Long idProduct) {
        List<ProductDetail> productDetails = productsDetailRespository.findByProductId(idProduct);
        return productDetails.stream().map(this::convertEntityToDto).collect(Collectors.toList());
    }

    @Override
    public ProductDto findProductByIdDetail(Long idProduct) {
        Product products = productsRepository.findByProductDetailsId(idProduct);
        return convertEntityToDto(products);
    }



    private ProductDetail convertEntityProductDetailToDto(ProductDetail productDetail) {
        return productDetail;
    }

    private ProductDto convertEntityToDto(Product product) {
        ProductDto productDto = new ProductDto();
        productDto.setId(product.getId());
        productDto.setName(product.getName());
        productDto.setDescription(product.getDescription());
        productDto.setImg(product.getImg());
        productDto.setStatus(product.getStatus());
        return productDto;
    }
    private ProductDetailDto convertEntityToDto(ProductDetail productdetail) {
        ProductDetailDto productDetailDto = new ProductDetailDto();
        productDetailDto.setId(productdetail.getId());
        productDetailDto.setColor(productdetail.getColor().getColorName());
        productDetailDto.setSize(productdetail.getSize().getSizeName());
        productDetailDto.setPrice(productdetail.getPrice());
        productDetailDto.setQuantity(productdetail.getQuantity());
        return productDetailDto;
    }
}
