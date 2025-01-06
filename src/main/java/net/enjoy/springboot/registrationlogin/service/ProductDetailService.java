package net.enjoy.springboot.registrationlogin.service;

import net.enjoy.springboot.registrationlogin.dto.ProductDetailDto;
import net.enjoy.springboot.registrationlogin.entity.ProductDetail;
import java.util.List;

public interface ProductDetailService {

    public List<ProductDetailDto> findDetailByIdDetail(Long idProduct);

    public ProductDetailDto getProductDetail(Long idDetail);

    public ProductDetail getProductDetailEntity(long idDetail);
    //API_ProductDetailController
    ProductDetailDto findProductDetailByProductId(Long productId);

    List<ProductDetailDto> getAllProductDetail();
}
