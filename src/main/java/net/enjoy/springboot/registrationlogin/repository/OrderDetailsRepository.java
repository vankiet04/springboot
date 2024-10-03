package net.enjoy.springboot.registrationlogin.repository;
import net.enjoy.springboot.registrationlogin.entity.OrderDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
public interface OrderDetailsRepository extends JpaRepository<OrderDetails, Long> {
    
}
