package net.enjoy.springboot.registrationlogin.service;

import net.enjoy.springboot.registrationlogin.dto.ProductDetailDto;
import net.enjoy.springboot.registrationlogin.entity.ProductDetail;
import net.enjoy.springboot.registrationlogin.repository.ProductsDetailRespository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProductDetailImpl implements ProductDetailService {
    ProductsDetailRespository productDetailRespository;

    public ProductDetailImpl(ProductsDetailRespository productDetailRespository) {
        this.productDetailRespository = productDetailRespository;
    }

    @Override
    public List<ProductDetailDto> findDetailByIdDetail(Long idProduct) {
        List<ProductDetail> productDetailDtos = productDetailRespository.findByProductId(idProduct);
        return productDetailDtos.stream().map(this::convertEntityToDto).collect(Collectors.toList());
    }


    public ProductDetailDto convertEntityToDto(ProductDetail productDetail) {
        ProductDetailDto productDetailDto = new ProductDetailDto();
        productDetailDto.setId(productDetail.getId());
        productDetailDto.setColor(productDetail.getColor().getColorName());
        productDetailDto.setSize(productDetail.getSize().getSizeName());
        productDetailDto.setQuantity(productDetail.getQuantity());
        return productDetailDto;
    }
}
