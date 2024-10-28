package com.DevSecOps.SpringProject.controller;


import com.DevSecOps.SpringProject.model.Employee;
import com.DevSecOps.SpringProject.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/employees")
public class EmployeeController {

    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @Autowired

    private final EmployeeService employeeService;

    @GetMapping("/{id}")
    public ResponseEntity<Employee> getAppareilById(@PathVariable Long id) {
        return employeeService.getEmployeeById(id).map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }


    @GetMapping
    public List<Employee> getAllEmployees(){
        return employeeService.GetAllEmployees();
    }

    @PostMapping
    public Employee saveEmployee(@RequestBody Employee employee){
        return  employeeService.addEmployee(employee);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Employee> updateEmployee(@PathVariable Long id, @RequestBody Employee employee){
        Employee updatedEmployee = employeeService.updateEmployee(id, employee);
        return updatedEmployee != null ? ResponseEntity.ok(updatedEmployee) : ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAppareil(@PathVariable Long id) {
        employeeService.deleteEmployee(id);
        return ResponseEntity.ok().build();
    }



}
