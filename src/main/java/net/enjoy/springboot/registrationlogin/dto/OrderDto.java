package net.enjoy.springboot.registrationlogin.dto;

import java.util.Date;
<<<<<<< HEAD
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
=======
import java.util.List;

public class OrderDto {
    private Long id;
    private double totalPrice;
    private Date orderTime;
    private Long userId;
    private List<OrderDetaildto> orderDetails;

    // Constructors
    public OrderDto() {}

    public OrderDto(Long id, double totalPrice, Date orderTime, Long userId, List<OrderDetaildto> orderDetails) {
        this.id = id;
        this.totalPrice = totalPrice;
        this.orderTime = orderTime;
        this.userId = userId;
        this.orderDetails = orderDetails;
    }

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }

    public Date getOrderTime() {
        return orderTime;
    }

    public void setOrderTime(Date orderTime) {
        this.orderTime = orderTime;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public List<OrderDetaildto> getOrderDetails() {
        return orderDetails;
    }

    public void setOrderDetails(List<OrderDetaildto> orderDetails) {
        this.orderDetails = orderDetails;
    }
>>>>>>> origin/main
}