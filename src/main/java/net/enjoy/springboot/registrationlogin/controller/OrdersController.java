package net.enjoy.springboot.registrationlogin.controller;

import java.sql.Date;

import org.springframework.security.access.method.P;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import net.enjoy.springboot.registrationlogin.dto.OrderDto;
// import user entity
import net.enjoy.springboot.registrationlogin.entity.User;
// user repo
import net.enjoy.springboot.registrationlogin.repository.UserRepository;
import org.springframework.stereotype.Controller;
// order entity
import net.enjoy.springboot.registrationlogin.entity.Order;
import net.enjoy.springboot.registrationlogin.entity.OrderDetails;
import net.enjoy.springboot.registrationlogin.entity.ProductDetail;
import net.enjoy.springboot.registrationlogin.repository.OrderDetailsRepository;
import net.enjoy.springboot.registrationlogin.repository.OrderRepository;
import net.enjoy.springboot.registrationlogin.repository.ProductsDetailRespository;

import org.springframework.beans.factory.annotation.Autowired;
import net.enjoy.springboot.registrationlogin.config.Cart;
import net.enjoy.springboot.registrationlogin.dto.CartItemDto;
import net.enjoy.springboot.registrationlogin.dto.OrderDetaildto;

@Controller
public class OrdersController {
    UserRepository userRepository;
    OrderRepository orderRepository;
    OrderDetailsRepository orderDetailsRepository;
    ProductsDetailRespository productsDetailRespository;

    @Autowired
    Cart cart;

    @Autowired
    public OrdersController(UserRepository userRepository, OrderRepository orderRepository, OrderDetailsRepository orderDetailsRepository, ProductsDetailRespository productsDetailRespository) {
        this.userRepository = userRepository;
        this.orderRepository = orderRepository;
        this.orderDetailsRepository = orderDetailsRepository;
        this.productsDetailRespository = productsDetailRespository;
    }

    @PostMapping("/order/add")
    @ResponseBody   
    public String order(double totalPrice) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User user = userRepository.findByEmail(authentication.getName());
        Order order = new Order();
        order.setUser(user);
        order.setOrderTime(new Date(System.currentTimeMillis()));
        order.setTotalPrice(totalPrice);
        orderRepository.save(order);

        // add order detail
        // get new order id
        Long orderId = order.getId();
        for (CartItemDto cartItemDto : cart.getItems()) {
            OrderDetails orderDetail = new OrderDetails();
            ProductDetail productDetail = productsDetailRespository.findById(cartItemDto.getIdDetail()).get();
            orderDetail.setOrder(order);
            orderDetail.setProductDetail(productDetail);
            orderDetail.setQuantity(cartItemDto.getQuantity());
            orderDetailsRepository.save(orderDetail);
        }

        // clear cart
        cart.clear();
        return "oke la";
    }
}
