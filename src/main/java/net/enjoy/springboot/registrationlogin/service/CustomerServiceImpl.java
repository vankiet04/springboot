package net.enjoy.springboot.registrationlogin.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import net.enjoy.springboot.registrationlogin.dto.CustomerDto;
import net.enjoy.springboot.registrationlogin.entity.Customer;
import net.enjoy.springboot.registrationlogin.repository.CustomerRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CustomerServiceImpl implements CustomerService {

    @Autowired
    private CustomerRepository customerRepository;

    public CustomerServiceImpl(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    @Override
    public List<CustomerDto> findAllCustomers() {
        List<Customer> customers =customerRepository.findCustomerz();
        return customers.stream().map(this::convertEntityToDto).collect(Collectors.toList());
    }

    @Override
    public CustomerDto findCustomerById(Long id) {
        Customer customer = customerRepository.findById(id);
        return convertEntityToDto(customer);
    }

    @Override
    public CustomerDto saveCustomer(CustomerDto customerDto) {
        Customer customer = convertDtoToEntity(customerDto);
        customer = customerRepository.save(customer);
        return convertEntityToDto(customer);
    }

    @Override
    public Customer updateCustomer(Customer customer) {
        return customerRepository.save(customer);
    }

    private CustomerDto convertEntityToDto(Customer customer) {
        return new CustomerDto(
                customer.getId(),
                customer.getFullName(),
                customer.getBirthDate(),
                customer.getPhoneNumber(),
                customer.getEmail(),
                customer.getStatus(),
                customer.getGender());
    }

    private Customer convertDtoToEntity(CustomerDto customerDto) {
        return new Customer(
                customerDto.getId(),
                customerDto.getFullName(),
                customerDto.getBirthDate(),
                customerDto.getPhoneNumber(),
                customerDto.getEmail(),
                customerDto.getGender(),
                customerDto.getStatus()
                );
    }

    @Override
    public CustomerDto findById(Long id) {
        Customer customer = customerRepository.findById(id);
        return convertEntityToDto(customer);
    }

    @Override
    public List<CustomerDto> findAllCustomerWithPage(int page) {
        Pageable pageable = PageRequest.of(page - 1, 5);
        Page<Customer> customers = customerRepository.findAllByStatus(pageable);

        return customers.map(this::convertEntityToDto).stream().collect(Collectors.toList());
    }
}