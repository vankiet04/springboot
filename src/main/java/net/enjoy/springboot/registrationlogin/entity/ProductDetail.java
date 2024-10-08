package net.enjoy.springboot.registrationlogin.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "productDetails")
public class ProductDetail {
    
  
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    @ManyToOne
    @JoinColumn(name = "color_id", nullable = false)
    private Color color;

    @ManyToOne
    @JoinColumn(name = "size_id", nullable = false)
    private Size size;

    @Column(nullable = false)
    private double price;

    @Column(nullable = false)
    private long quantity;

    @ManyToOne
    @JoinColumn(name = "product_id", nullable = false)
    private Product product;
}
