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
@Table(name = "import_details")
public class ImportDetail {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; 

    @ManyToOne
    @JoinColumn(name = "import_id", nullable = false)
    private ImportProduct importProduct;

    @ManyToOne
    @JoinColumn(name = "productdetail_id", nullable = false)
    private ProductDetail productDetail;

    @Column(nullable = false)
    private int quantity;
}
