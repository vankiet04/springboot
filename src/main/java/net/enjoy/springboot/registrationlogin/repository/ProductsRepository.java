package net.enjoy.springboot.registrationlogin.repository;

import net.enjoy.springboot.registrationlogin.dto.ProductDto;
import net.enjoy.springboot.registrationlogin.entity.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ProductsRepository extends PagingAndSortingRepository<Product, Long> {
    @Query("SELECT p FROM Product p JOIN p.productDetails pd WHERE " +
            "(:sizeId IS NULL OR pd.size.id = :sizeId) AND " +
            "(:colorId IS NULL OR pd.color.id = :colorId) AND " +
            "(:categoryId IS NULL OR p.category.id = :categoryId) AND " +
            "(:minPrice IS NULL OR pd.price >= :minPrice) AND " +
            "(:maxPrice IS NULL OR pd.price <= :maxPrice) AND " +
            "(:name IS NULL OR p.name LIKE CONCAT('%', :name, '%'))")
    Page<Product> findbyFilter(
            @Param("sizeId") Long sizeId,
            @Param("colorId") Long colorId,
            @Param("categoryId") Long categoryId,
            @Param("minPrice") Long minPrice,
            @Param("maxPrice") Long maxPrice,
            Pageable pageable,
            @Param("name") String name
    );


    Product findById(Long id);

    @Query("SELECT p FROM Product p JOIN p.productDetails pd WHERE pd.id = :id")
    Product findByProductDetailsId(Long id);
}
