package net.enjoy.springboot.registrationlogin.service;
import net.enjoy.springboot.registrationlogin.dto.DetailCartItemDto;
import net.enjoy.springboot.registrationlogin.entity.User;
import java.util.List;
import net.enjoy.springboot.registrationlogin.repository.OrderRepository;
// import order
import net.enjoy.springboot.registrationlogin.entity.Order;
public class OrdersServiceImpl implements OrdersService {
    OrderRepository orderRepository;


    public OrdersServiceImpl(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    public void saveOrder(User user, List<DetailCartItemDto> detailCartItemDtos) {
        double totalPrice = 0;
        for (DetailCartItemDto detailCartItemDto : detailCartItemDtos) {
            totalPrice += detailCartItemDto.getQuantity() * detailCartItemDto.getProductDetail().getPrice();
        }
        Order order = new Order();
        order.setUser(user);
        order.setTotalPrice(totalPrice);
        orderRepository.save(order);
    }
    
}
