package net.enjoy.springboot.registrationlogin.service;

import net.enjoy.springboot.registrationlogin.dto.ProductDetailDto;
import net.enjoy.springboot.registrationlogin.dto.ProductDto;
import net.enjoy.springboot.registrationlogin.entity.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import net.enjoy.springboot.registrationlogin.entity.ProductDetail;
import java.util.List;
import java.util.Map;

public interface ProductService {
    Page<ProductDto> findAllProduct(Pageable pageable);

    List<ProductDto> findAllProduct();

    ProductDto findById(Long id);
   Product findProductById(Long id);
    void saveProduct(Product product);

    List<ProductDetailDto> findDetailById(Long idProduct);

    public Page<ProductDto> searchProduct(Long sizeId, Long colorId, Long categoryId, Long minPrice, Long maxPrice, Pageable pageable, String name);

    public Page<ProductDto> searchProductPriceAsc(Long sizeId, Long colorId, Long categoryId, Long minPrice, Long maxPrice, Pageable pageable, String name);

    public Page<ProductDto> searchProductPriceDesc(Long sizeId, Long colorId, Long categoryId, Long minPrice, Long maxPrice, Pageable pageable, String name);

    public ProductDto findProductByIdDetail(Long idProduct);

    public List<ProductDto> findAllProductWithPage(int page);

    public Product addProductAPI(Product product);

    public Product updateProductAPI(Product product);

    public List<Map<String, Object>> getAllProductWithAllDetails();
}
