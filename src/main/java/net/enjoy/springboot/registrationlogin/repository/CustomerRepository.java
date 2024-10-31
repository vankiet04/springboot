package net.enjoy.springboot.registrationlogin.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import net.enjoy.springboot.registrationlogin.entity.Customer;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {
}