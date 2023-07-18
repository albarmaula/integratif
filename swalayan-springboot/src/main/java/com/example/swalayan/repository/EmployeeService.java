package com.example.swalayan.repository;

import com.example.swalayan.model.Employee;
import com.example.swalayan.response.Response;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Optional;

@Service
@Transactional

public class EmployeeService {
    @Autowired
    public EmployeeRepository employeeRepository;
    private RestTemplate restTemplate;

    public void deleteEmployee(Long NIP){
        employeeRepository.deleteById(NIP);
    }

    public String save (Employee employee){
        try {
            employeeRepository.save(employee);
            return "Berhasil tambah data !";
        } catch (Exception e) {
            return e.getMessage();
        }
    }

    public Object findEmployee (Long NIP){
        return employeeRepository.findByNip(NIP)
                .orElseThrow(() -> new RuntimeException("Employee not found with NIP: " + NIP));
    }

    public Iterable<Employee> findAll(){
        return employeeRepository.findAll();
    }

}
