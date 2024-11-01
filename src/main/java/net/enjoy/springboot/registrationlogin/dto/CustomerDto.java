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

public class CustomerDto {
    private Long id; // Mã nhân viên (manv)
    private String fullName; // Họ tên (hoten)
    private LocalDate birthDate; // Ngày sinh (ngaysinh)
    private String phoneNumber; // Số điện thoại (sdt)
    private String email; // Email (email)
    private int status; // Trạng thái (trangthai)
    private String gender; // Giới tính (gender)
    
}
