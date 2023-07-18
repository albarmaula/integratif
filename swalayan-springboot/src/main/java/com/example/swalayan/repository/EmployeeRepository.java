package com.example.swalayan.repository;

import com.example.swalayan.model.Employee;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface EmployeeRepository extends CrudRepository <Employee, Long> {
    Optional<Object> findByNip(Long nip);
    //List<Employee> findByEmpName(String empName);

}
