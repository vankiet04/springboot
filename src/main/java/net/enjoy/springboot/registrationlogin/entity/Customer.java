package net.enjoy.springboot.registrationlogin.entity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "customers")
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; // Mã nhân viên (manv)

    @Column(nullable = false)
    private String fullName; // Họ tên (hoten)

    @Column(nullable = false)
    private LocalDate birthDate; // Ngày sinh (ngaysinh)

    @Column(nullable = false, unique = true)
    private String phoneNumber; // Số điện thoại (sdt)

    @Column(nullable = false, unique = true)
    private String email; // Email (email)

    @Column(nullable = false)
    private String gender; // Giới tính (gender)

    @Column(nullable = false)
    private String status; // Trạng thái (trangthai)
}
