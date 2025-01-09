package net.enjoy.springboot.registrationlogin.service;

import net.enjoy.springboot.registrationlogin.dto.DetailCartItemDto;
import net.enjoy.springboot.registrationlogin.dto.OrderDto;
// import entity user
import net.enjoy.springboot.registrationlogin.entity.User;
import java.util.List;
import java.util.Map;
import net.enjoy.springboot.registrationlogin.entity.Order;
import net.enjoy.springboot.registrationlogin.dto.OrderDto;



public interface OrdersService {
    public void saveOrder(User user, List<DetailCartItemDto> detailCartItemDtos);

    List<OrderDto> findAllOrders();

}

    
