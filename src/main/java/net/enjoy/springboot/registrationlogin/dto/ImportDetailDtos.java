package net.enjoy.springboot.registrationlogin.dto;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ImportDetailDtos {
    private Long id; 
    private Long importId; 
    private String tensanpham;
    // size, mau
    private String size;
    private String mau;
    private int quantity;
    private double importPrice;
    private double exportPrice;
}
