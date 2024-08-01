package net.enjoy.springboot.registrationlogin.entity;

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

    @Column(nullable = false)
    private String color;

    @Column(nullable = false)
    private String size;

    @Column(nullable = false)
    private double price;

    @Column(nullable = false)
    private long quantity;

    @Column(nullable = false)
    private int status;

    @ManyToOne
    @JoinColumn(name = "product_id", nullable = false)
    private Product product;
}
