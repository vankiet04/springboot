package net.enjoy.springboot.registrationlogin.service;

import net.enjoy.springboot.registrationlogin.dto.DetailCartItemDto;
import net.enjoy.springboot.registrationlogin.dto.OrderDto;
<<<<<<< HEAD
=======
import net.enjoy.springboot.registrationlogin.entity.User;
import java.util.List;
import java.util.stream.Collectors;

import net.enjoy.springboot.registrationlogin.repository.OrderRepository;
// import order
>>>>>>> origin/main
import net.enjoy.springboot.registrationlogin.entity.Order;
import net.enjoy.springboot.registrationlogin.entity.User;
import net.enjoy.springboot.registrationlogin.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class OrdersServiceImpl implements OrdersService {

    private final OrderRepository orderRepository;

    @Autowired
    public OrdersServiceImpl(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    @Override
    public List<OrderDto> findAllOrders() {
        List<Order> orders = orderRepository.findAll();
        return orders.stream()
                .map(this::convertEntityToDto)
                .collect(Collectors.toList());
    }

<<<<<<< HEAD
    private OrderDto convertEntityToDto(Order order) {
        OrderDto orderDto = new OrderDto();
        orderDto.setId(order.getId());
        orderDto.setUserId(order.getUser().getId());
        orderDto.setOrderDate(order.getOrderTime());
        orderDto.setTotalPrice(order.getTotalPrice());
        return orderDto;
    }

    @Override
    public void saveOrder(User user, List<DetailCartItemDto> detailCartItemDtos) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'saveOrder'");
    }
=======
    @Override
    public List<OrderDto> findAllOrders() {
        List<Order> orders = orderRepository.findAll();
        List<OrderDto> orderDtos = orders.stream().map(this::convertToOrderDto).collect(Collectors.toList());
        return orderDtos;

    }

        // TODO Auto-generated method stub

    
    public OrderDto convertToOrderDto(Order order) {
        OrderDto orderDto = new OrderDto();
        orderDto.setId(order.getId());
        orderDto.setTotalPrice(order.getTotalPrice());
        orderDto.setOrderTime(order.getOrderTime());
        orderDto.setUserId(order.getUser().getId());
        orderDto.setOrderDetails(null);
        return orderDto;
    }

>>>>>>> origin/main

    
}