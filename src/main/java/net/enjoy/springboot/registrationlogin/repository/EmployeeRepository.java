package net.enjoy.springboot.registrationlogin.repository;

import net.enjoy.springboot.registrationlogin.entity.Employee;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmployeeRepository extends PagingAndSortingRepository<Employee, Long> {
    @Query("SELECT e FROM Employee e WHERE e.status = 1")
    Page<Employee> findAllByStatus(Pageable pageable);

    @Query("SELECT e FROM Employee e")
    List<Employee> findEmployeez();

    Employee save(Employee employee);

    Employee findById(Long id);
}