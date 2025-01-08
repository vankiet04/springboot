package net.enjoy.springboot.registrationlogin.service;

import net.enjoy.springboot.registrationlogin.dto.DetailCartItemDto;
import net.enjoy.springboot.registrationlogin.dto.OrderDto;
// import entity user
import net.enjoy.springboot.registrationlogin.entity.User;
import java.util.List;

public interface OrdersService {
    public void saveOrder(User user, List<DetailCartItemDto> detailCartItemDtos);
    public List<OrderDto> findAllOrders();
}

    

    
