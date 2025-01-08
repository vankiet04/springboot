package net.enjoy.springboot.registrationlogin.controller;

import net.enjoy.springboot.registrationlogin.dto.OrderDto;
import net.enjoy.springboot.registrationlogin.service.OrdersService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:8080")
@RestController
@RequestMapping("/api/orders")
public class API_OrderController {

    @Autowired
    private OrdersService orderService;

    @GetMapping("/getall")
    public List<OrderDto> getAllBills() {
        List<OrderDto> orders = orderService.findAllOrders();
        return  orders;
    }

 
}