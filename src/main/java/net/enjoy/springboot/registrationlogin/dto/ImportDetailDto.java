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
public class ImportDetailDto {
    private Long id; 
    private Long importId; 
    private Long productDetailId; 
    private int quantity; 
    private double importPrice;
    private double exportPrice;
}