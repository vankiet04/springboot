package net.enjoy.springboot.registrationlogin.controller;

import jakarta.servlet.http.HttpSession;
import net.enjoy.springboot.registrationlogin.config.Cart;
import net.enjoy.springboot.registrationlogin.dto.CartItemDto;
import net.enjoy.springboot.registrationlogin.dto.DetailCartItemDto;
import net.enjoy.springboot.registrationlogin.dto.ProductDto;
import net.enjoy.springboot.registrationlogin.entity.ProductDetail;
import net.enjoy.springboot.registrationlogin.repository.ProductsDetailRespository;
import net.enjoy.springboot.registrationlogin.service.ProductDetailService;
import net.enjoy.springboot.registrationlogin.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.List;
// import productdetaildto
import net.enjoy.springboot.registrationlogin.dto.ProductDetailDto;

@Controller
public class CartController {
    ProductService productService;
    ProductDetailService productDetailService;
    ProductController productController;
    
    @Autowired  
    Cart cart;

    @Autowired
    public void setProductService(ProductService productService) {
        this.productService = productService;
    }

    @Autowired
    public void setProductController(ProductController productController) {
        this.productController = productController;
    }

    @Autowired
    public void setProductDetailService(ProductDetailService productDetailService) {
        this.productDetailService = productDetailService;
    }

    @PostMapping("/cart/add")
    @ResponseBody
    public List<CartItemDto> addCart(@RequestBody CartItemDto cartItemDto, HttpSession session) {
        cart.addItem(cartItemDto);
        return cart.getItems();
    }

    @PostMapping("/cart/getCart")
    @ResponseBody
    public List<DetailCartItemDto> getCart() {
        List<DetailCartItemDto> detailCartItemDtos = new ArrayList<>();

        for (CartItemDto cartItemDto : cart.getItems()) {
            DetailCartItemDto detailCartItemDto = new DetailCartItemDto();
            ProductDto productDto = productService.findProductByIdDetail(cartItemDto.getIdDetail());
            detailCartItemDto.setProduct(productDto);
            detailCartItemDto.setQuantity(cartItemDto.getQuantity());
            ProductDetailDto productDetailDto = productDetailService.getProductDetail(cartItemDto.getIdDetail());
            detailCartItemDto.setProductDetail(productDetailDto);
            detailCartItemDtos.add(detailCartItemDto);
        }
        return detailCartItemDtos;
    }

    @PostMapping("/cart/getProductByIdDetail")
    @ResponseBody
    public List<ProductDto> getProductByIdDetail(List<CartItemDto> cartItemDtos) {
        List<ProductDto> products = new ArrayList<>();
        for (CartItemDto cartItemDto : cartItemDtos) {
            ProductDto productDto = productService.findProductByIdDetail(cartItemDto.getIdDetail());
            products.add(productDto);
        }
        return products;
    }

}
