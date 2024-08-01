package net.enjoy.springboot.registrationlogin.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ProductDetailDto {
    private Long id;
    private String color;
    private String size;
    private double price;
    private long quantity;
//    private Long productId;
//    private int status;
}
