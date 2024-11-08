package net.enjoy.springboot.registrationlogin.repository;

import net.enjoy.springboot.registrationlogin.entity.Supplier;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SupplierRepository extends PagingAndSortingRepository<Supplier, Long> {
    @Query("SELECT s FROM Supplier s WHERE s.status = 1")
    Page<Supplier> findAllByStatus(Pageable pageable);

    @Query("SELECT s FROM Supplier s")
    List<Supplier> findSupplierz();

    Supplier save(Supplier supplier);

    Supplier findById(Long id);
}