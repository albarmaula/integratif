package com.example.swalayan.controller;

import com.example.swalayan.model.Employee;
import com.example.swalayan.repository.EmployeeRepository;
import com.example.swalayan.repository.EmployeeService;
import com.example.swalayan.response.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("api/employee")
public class EmployeeController {

    private final EmployeeService employeeService;
    @Autowired
    public EmployeeRepository employeeRepository;

    @Autowired
    public EmployeeController(EmployeeService employeeService){
        this.employeeService = employeeService;
    }

    @GetMapping
    public Iterable<Employee> findAllEmployee(){
        return employeeService.findAll();
    }

    @GetMapping("/{nip}")
    public Object findEmployee(@PathVariable("nip") Long nip){
        return employeeService.findEmployee(nip);
    }

    @PostMapping("/create")
    public String addEmploye(@RequestBody Employee employee){
        return employeeService.save(employee);
    }

    @PutMapping
    public String updateEmployee(@RequestBody Employee employee){
        return employeeService.save(employee);
    }

    @DeleteMapping("/{NIP}")
    public void deleteEmployee(@PathVariable("NIP")Long NIP){
        employeeService.deleteEmployee(NIP);
    }
}
