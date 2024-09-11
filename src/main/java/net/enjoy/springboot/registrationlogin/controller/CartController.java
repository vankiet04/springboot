package net.enjoy.springboot.registrationlogin.controller;

import jakarta.servlet.http.HttpSession;
import net.enjoy.springboot.registrationlogin.dto.CartItemDto;
import net.enjoy.springboot.registrationlogin.dto.ProductDto;
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

@Controller
public class CartController {
    ProductService productService;
    ProductController productController;

    @Autowired
    public void setProductService(ProductService productService) {
        this.productService = productService;
    }

    @Autowired
    public void setProductController(ProductController productController) {
        this.productController = productController;
    }

    @PostMapping("/cart/add")
    @ResponseBody
    public List<CartItemDto> addCart(@RequestBody CartItemDto cartItemDto, HttpSession session) {
        List<CartItemDto> cart = (List<CartItemDto>) session.getAttribute("cart");
        if (cart == null) {
            cart = new ArrayList<>();
            cart.add(cartItemDto);
        } else {
            boolean isExist = false;
            for (CartItemDto item : cart) {
                if (item.getIdDetail() == cartItemDto.getIdDetail()) {
                    item.setQuantity(item.getQuantity() + cartItemDto.getQuantity());
                    isExist = true;
                    break;
                }
            }
            if (!isExist) {
                cart.add(cartItemDto);
            }
        }
        session.setAttribute("cart", cart);

        return cart;
    }

    @PostMapping("/cart/getCart")
    @ResponseBody
    public List<CartItemDto> getCart(HttpSession session) {
        List<CartItemDto> cart = (List<CartItemDto>) session.getAttribute("cart");
        return cart;
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
