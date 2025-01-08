package net.enjoy.springboot.registrationlogin.dto;

import java.util.Date;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class OrderDto {
    private Long id;
    private Long userId;
    private Date orderDate;
    private double totalPrice;

    public OrderDto(){
        
    }

    // Constructor
    public OrderDto(Long id, Long userId, Date orderDate, double totalPrice) {
        this.id = id;
        this.userId = userId;
        this.orderDate = orderDate;
        this.totalPrice = totalPrice;
    }
}