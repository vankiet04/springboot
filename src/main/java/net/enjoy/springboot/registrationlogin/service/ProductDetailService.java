package net.enjoy.springboot.registrationlogin.service;

import net.enjoy.springboot.registrationlogin.dto.ProductDetailDto;

import java.util.List;

public interface ProductDetailService {

    public List<ProductDetailDto> findDetailByIdDetail(Long idProduct);
    public ProductDetailDto getProductDetail(Long idDetail);
}
