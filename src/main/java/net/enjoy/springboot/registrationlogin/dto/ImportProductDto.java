package net.enjoy.springboot.registrationlogin.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ImportProductDto {
    private Long id; // Mã phiếu nhập (id)
    private LocalDateTime importDate; // Thời gian nhập (importDate)
    private Long supplierId; // Mã nhà cung cấp (manhacungcap)
    private Long employeeId; // Mã nhân viên (manhanvien)
    private double totalAmount; // Tổng tiền (tongtien)
    private String status; // Trạng thái (trangthai)
}