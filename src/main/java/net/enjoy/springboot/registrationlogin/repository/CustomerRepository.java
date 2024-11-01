package net.enjoy.springboot.registrationlogin.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import net.enjoy.springboot.registrationlogin.entity.Customer;

@Repository
public interface CustomerRepository extends PagingAndSortingRepository<Customer, Long> {
    @Query("SELECT c FROM Customer c WHERE c.status = 1")
    Page<Customer> findAllByStatus(Pageable pageable);

    @Query("SELECT c FROM Customer c")
    List<Customer> findCustomerz();

    Customer findById(Long id);

    Customer save(Customer customer);
}