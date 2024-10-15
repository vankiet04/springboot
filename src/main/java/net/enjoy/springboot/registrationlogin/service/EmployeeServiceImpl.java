package net.enjoy.springboot.registrationlogin.service;

import net.enjoy.springboot.registrationlogin.dto.EmployeeDto;
import net.enjoy.springboot.registrationlogin.entity.Employee;
import net.enjoy.springboot.registrationlogin.repository.EmployeeRepository;
import net.enjoy.springboot.registrationlogin.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    @Autowired
    private EmployeeRepository employeeRepository;

    @Override
    public List<EmployeeDto> findAllEmployees() {
        return employeeRepository.findAll().stream()
                .map(this::convertEntityToDto)
                .collect(Collectors.toList());
    }

    @Override
    public EmployeeDto findEmployeeById(Long id) {
        return employeeRepository.findById(id)
                .map(this::convertEntityToDto)
                .orElse(null);
    }

    @Override
    public EmployeeDto saveEmployee(EmployeeDto employeeDto) {
        Employee employee = convertDtoToEntity(employeeDto);
        employee = employeeRepository.save(employee);
        return convertEntityToDto(employee);
    }

    @Override
    public Employee updateEmployee(Employee employee) {
        return employeeRepository.save(employee);
    }

    private EmployeeDto convertEntityToDto(Employee employee) {
        return new EmployeeDto(
                employee.getId(),
                employee.getFullName(),
                employee.getBirthDate(),
                employee.getPhoneNumber(),
                employee.getEmail(),
                employee.getStatus(),
                employee.getGender()
        );
    }

    private Employee convertDtoToEntity(EmployeeDto employeeDto) {
        return new Employee(
                employeeDto.getId(),
                employeeDto.getFullName(),
                employeeDto.getBirthDate(),
                employeeDto.getPhoneNumber(),
                employeeDto.getEmail(),
                employeeDto.getStatus(),
                employeeDto.getGender());
    }
    @Override
    public Employee findById(Long id) {
        return employeeRepository.findById(id).orElse(null);
    }
}