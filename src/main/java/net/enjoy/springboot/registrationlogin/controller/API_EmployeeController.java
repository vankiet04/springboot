package net.enjoy.springboot.registrationlogin.controller;

import net.enjoy.springboot.registrationlogin.dto.CustomerDto;
import net.enjoy.springboot.registrationlogin.dto.EmployeeDto;
import net.enjoy.springboot.registrationlogin.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import org.springframework.security.core.Authentication;
import net.enjoy.springboot.registrationlogin.entity.User;
import net.enjoy.springboot.registrationlogin.service.UserService;
@CrossOrigin(origins = "http://localhost:8080")
@RestController
@RequestMapping("/api/employees")
public class API_EmployeeController {

    @Autowired
    private EmployeeService employeeService;

    @Autowired
    private UserService userService;

    @GetMapping("/getall")
    public List<EmployeeDto> getAllEmployees() {
        return employeeService.findAllEmployees();
    }

     @GetMapping("/getEmployeePage")
    public List<EmployeeDto> getEmployeeWithPage(@RequestParam(defaultValue = "1") int page) {
        return employeeService.findAllEmployeeWithPage(page);
    }

    @GetMapping("/{id}")
    public ResponseEntity<EmployeeDto> getEmployeeById(@PathVariable Long id) {
        EmployeeDto employee = employeeService.findEmployeeById(id);
        if (employee != null) {
            return new ResponseEntity<>(employee, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/add")
    public ResponseEntity<EmployeeDto> addEmployee(@RequestBody EmployeeDto employeeDto) {
        EmployeeDto newEmployee = employeeService.saveEmployee(employeeDto);
        return new ResponseEntity<>(newEmployee, HttpStatus.CREATED);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<EmployeeDto> updateEmployee(@PathVariable Long id, @RequestBody EmployeeDto employeeDto) {
        EmployeeDto existingEmployee = employeeService.findEmployeeById(id);
        if (existingEmployee != null) {
            employeeDto.setId(id);
            EmployeeDto updatedEmployee = employeeService.saveEmployee(employeeDto);
            return new ResponseEntity<>(updatedEmployee, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/updateStatus/{id}")
    public ResponseEntity<EmployeeDto> updateEmployeeStatus(@PathVariable Long id, @RequestBody EmployeeDto employeeDto) {
        EmployeeDto existingEmployee = employeeService.findEmployeeById(id);
        if (existingEmployee != null) {
            existingEmployee.setStatus(employeeDto.getStatus());
            EmployeeDto updatedEmployee = employeeService.saveEmployee(existingEmployee);
            return new ResponseEntity<>(updatedEmployee, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/current")
    public ResponseEntity<User> getCurrentEmployee() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        Authentication authentication = new UsernamePasswordAuthenticationToken(auth.getPrincipal(), auth.getCredentials(), auth.getAuthorities());
        SecurityContextHolder.getContext().setAuthentication(authentication);
    
        if (authentication != null && authentication.isAuthenticated()) {
            Object principal = authentication.getPrincipal();
            if (principal instanceof UserDetails) {
                String username = ((UserDetails) principal).getUsername();
                User user = userService.findUserByEmail(username);
                if (user != null) {
                    return new ResponseEntity<>(user, HttpStatus.OK);
                }
            }
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}