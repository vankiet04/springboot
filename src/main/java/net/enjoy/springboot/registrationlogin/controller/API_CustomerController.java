package net.enjoy.springboot.registrationlogin.controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import net.enjoy.springboot.registrationlogin.dto.CustomerDto;
import net.enjoy.springboot.registrationlogin.service.CustomerService;

import java.util.List;

@CrossOrigin(origins = "http://localhost:8080")
@RestController
@RequestMapping("/api/customers")   
public class API_CustomerController {

    @Autowired
    private CustomerService customerService;

    @GetMapping("/getall")
    public List<CustomerDto> getAllCustomers() {
        return customerService.findAllCustomers();
        
    }

    @GetMapping("/getCustomerPage")
    public List<CustomerDto> getCustomerWithPage(@RequestParam(defaultValue = "1") int page) {
        return customerService.findAllCustomerWithPage(page);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CustomerDto> getCustomerById(@PathVariable Long id) {
        CustomerDto customer = customerService.findCustomerById(id);
        if (customer != null) {
            return new ResponseEntity<>(customer, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/add")
    public ResponseEntity<CustomerDto> addCustomer(@RequestBody CustomerDto customerDto) {
        CustomerDto newCustomer = customerService.saveCustomer(customerDto);
        return new ResponseEntity<>(newCustomer, HttpStatus.CREATED);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<CustomerDto> updateCustomer(@PathVariable Long id, @RequestBody CustomerDto customerDto) {
        CustomerDto existingCustomer = customerService.findCustomerById(id);
        if (existingCustomer != null) {
            customerDto.setId(id);
            CustomerDto updatedCustomer = customerService.saveCustomer(customerDto);
            return new ResponseEntity<>(updatedCustomer, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/updateStatus/{id}")
    public ResponseEntity<CustomerDto> updateCustomerStatus(@PathVariable Long id, @RequestBody CustomerDto customerDto) {
        CustomerDto existingCustomer = customerService.findCustomerById(id);
        if (existingCustomer != null) {
            existingCustomer.setStatus(customerDto.getStatus());
            CustomerDto updatedCustomer = customerService.saveCustomer(existingCustomer);
            return new ResponseEntity<>(updatedCustomer, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}