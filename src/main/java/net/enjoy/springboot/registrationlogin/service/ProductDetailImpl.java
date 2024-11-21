package net.enjoy.springboot.registrationlogin.service;

import net.enjoy.springboot.registrationlogin.dto.ProductDetailDto;
import net.enjoy.springboot.registrationlogin.entity.ProductDetail;
import net.enjoy.springboot.registrationlogin.repository.ProductsDetailRespository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
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

    @Override
    public ProductDetailDto getProductDetail(Long idDetail) {
        ProductDetail productDetail = productDetailRespository.findById(idDetail).get();
        return convertEntityToDto(productDetail);
    }


    public ProductDetailDto convertEntityToDto(ProductDetail productDetail) {
        ProductDetailDto productDetailDto = new ProductDetailDto();
        productDetailDto.setId(productDetail.getId());
        productDetailDto.setColor(productDetail.getColor().getColorName());
        productDetailDto.setSize(productDetail.getSize().getSizeName());
        productDetailDto.setPrice(productDetail.getPrice());
        productDetailDto.setQuantity(productDetail.getQuantity());
        return productDetailDto;
    }

    @Override
    public ProductDetailDto findProductDetailByProductId(Long productId) {
        ProductDetail productDetail = productDetailRespository.findByProductDeaId(productId);
        if (productDetail != null) {
            return convertEntityToDto(productDetail);
        } else {
            return null;
        }
    }

    @Override
    public ArrayList<ProductDetailDto> findListProductDetailByProductId(Long productId) {
        List<ProductDetail> productDetailList = productDetailRespository.findByProductId(productId);
        ArrayList<ProductDetailDto> productDetailDtos = new ArrayList<>();
        for (ProductDetail productDetail : productDetailList) {
            productDetailDtos.add(convertEntityToDto(productDetail));
        }
        return productDetailDtos;
    }

    

   
}

