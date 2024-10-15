package net.enjoy.springboot.registrationlogin.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class SupplierDto {
    private Long id; // Mã nhà cung cấp (manhacungcap)
    private String name; // Tên nhà cung cấp (tennhacungcap)
    private String address; // Địa chỉ (diachi)
    private String email; // Email (email)
    private String phoneNumber; // Số điện thoại (sdt)
    private String status; // Trạng thái (trangthai)
}