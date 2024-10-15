package net.enjoy.springboot.registrationlogin.entity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "import_products")
public class ImportProduct {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; // Mã phiếu nhập (id)

    @Column(nullable = false)
    private LocalDateTime importDate; // Thời gian nhập (importDate)

    @ManyToOne
    @JoinColumn(name = "supplier_id", nullable = false)
    private Supplier supplier; // Mã nhà cung cấp (manhacungcap)

    @ManyToOne
    @JoinColumn(name = "employee_id", nullable = false)
    private Employee employee; // Mã nhân viên (manv)

    @Column(nullable = false)
    private double totalAmount; // Tổng tiền (tongtien)

    @Column(nullable = false)
    private String status; // Trạng thái (trangthai)
}