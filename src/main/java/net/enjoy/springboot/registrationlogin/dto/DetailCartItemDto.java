package net.enjoy.springboot.registrationlogin.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import net.enjoy.springboot.registrationlogin.entity.Product;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class DetailCartItemDto {
    // combine all the fields from productdto, and quantity
    ProductDto product;
    ProductDetailDto productDetail; 
    long quantity;
}
