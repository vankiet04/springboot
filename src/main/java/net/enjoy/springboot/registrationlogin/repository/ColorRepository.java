package net.enjoy.springboot.registrationlogin.repository;
import net.enjoy.springboot.registrationlogin.entity.Color;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ColorRepository extends JpaRepository<Color, Long> {
    
}
