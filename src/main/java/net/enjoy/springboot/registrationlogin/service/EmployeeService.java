package net.enjoy.springboot.registrationlogin.service;

import net.enjoy.springboot.registrationlogin.dto.EmployeeDto;
import net.enjoy.springboot.registrationlogin.entity.Employee;

import java.util.List;

public interface EmployeeService {
    List<EmployeeDto> findAllEmployees();
    EmployeeDto findById(Long id);
    EmployeeDto findEmployeeById(Long id);
    EmployeeDto saveEmployee(EmployeeDto employeeDto);
    Employee updateEmployee(Employee employee);
    public List<EmployeeDto> findAllEmployeeWithPage(int page);
}