package net.enjoy.springboot.registrationlogin.service;

import net.enjoy.springboot.registrationlogin.dto.ProductDetailDto;
import net.enjoy.springboot.registrationlogin.dto.ProductDto;
import net.enjoy.springboot.registrationlogin.entity.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import net.enjoy.springboot.registrationlogin.entity.ProductDetail;
import java.util.List;

public interface ProductService {
    Page<ProductDto> findAllProduct(Pageable pageable);

    ProductDto findById(Long id);

    void saveProduct(Product product);

    List<ProductDetailDto> findDetailById(Long idProduct);

    public Page<ProductDto> searchProduct(Long sizeId, Long colorId, Long categoryId, long minPrice, long maxPrice, Pageable pageable, String name);

    public ProductDto findProductByIdDetail(Long idProduct);


    //API_ProductController
    public List<ProductDto> getAllProducts();
    public void updateProductStatus(Long id, int status);
    // end API_ProductController


}
