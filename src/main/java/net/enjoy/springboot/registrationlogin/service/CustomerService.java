package net.enjoy.springboot.registrationlogin.service;

import java.util.List;

import net.enjoy.springboot.registrationlogin.dto.CustomerDto;
import net.enjoy.springboot.registrationlogin.dto.ProductDto;
import net.enjoy.springboot.registrationlogin.entity.Customer;

public interface CustomerService {
    List<CustomerDto> findAllCustomers();
    CustomerDto findById(Long id);
    CustomerDto findCustomerById(Long id);
    CustomerDto saveCustomer(CustomerDto customerDto);
    Customer updateCustomer(Customer customer);
    public List<CustomerDto> findAllCustomerWithPage(int page);
}