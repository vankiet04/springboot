package net.enjoy.springboot.registrationlogin.service;
import org.springframework.beans.factory.annotation.Autowired;
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

    @Override
    public List<CustomerDto> findAllCustomers() {
        return customerRepository.findAll().stream()
                .map(this::convertEntityToDto)
                .collect(Collectors.toList());
    }

    @Override
    public CustomerDto findCustomerById(Long id) {
        return customerRepository.findById(id)
                .map(this::convertEntityToDto)
                .orElse(null);
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
                customer.getGender()
        );
    }

    private Customer convertDtoToEntity(CustomerDto customerDto) {
        return new Customer(
            customerDto.getId(),
            customerDto.getFullName(),
            customerDto.getBirthDate(),
            customerDto.getPhoneNumber(),
            customerDto.getEmail(),
            customerDto.getStatus(),
            customerDto.getGender());
    }
    @Override
    public Customer findById(Long id) {
        return customerRepository.findById(id).orElse(null);
    }
}