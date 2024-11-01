package net.enjoy.springboot.registrationlogin.service;

import net.enjoy.springboot.registrationlogin.dto.EmployeeDto;
import net.enjoy.springboot.registrationlogin.entity.Employee;
import net.enjoy.springboot.registrationlogin.repository.EmployeeRepository;
import net.enjoy.springboot.registrationlogin.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    @Autowired
    private EmployeeRepository employeeRepository;

    public EmployeeServiceImpl(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    @Override
    public List<EmployeeDto> findAllEmployees() {
        List<Employee> employees = employeeRepository.findEmployeez();
        return employees.stream().map(this::convertEntityToDto).collect(Collectors.toList());
    }

    @Override
    public EmployeeDto findEmployeeById(Long id) {
       Employee employee = employeeRepository.findById(id);
        return convertEntityToDto(employee);
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
                employeeDto.getGender(),
                employeeDto.getStatus()
        );
    }
    @Override
    public EmployeeDto findById(Long id) {
        Employee employee = employeeRepository.findById(id);
        return convertEntityToDto(employee);
    }

    @Override
    public List<EmployeeDto> findAllEmployeeWithPage(int page){
        Pageable pageable = PageRequest.of(page - 1, 5);
        Page<Employee> employees = employeeRepository.findAllByStatus(pageable);

        return employees.map(this::convertEntityToDto).stream().collect(Collectors.toList());
    }
}