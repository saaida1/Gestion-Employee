package com.DevSecOps.SpringProject.service;

import com.DevSecOps.SpringProject.model.Employee;
import com.DevSecOps.SpringProject.repository.EmployeeRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EmployeeService {


    public EmployeeService(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    private final EmployeeRepository employeeRepository;

    //Get the list of employees
    public List<Employee> GetAllEmployees(){
        return employeeRepository.findAll();
    }

    //Get an employee by Id

    public Optional<Employee>  getEmployeeById(Long id){
        return employeeRepository.findById(id);
    }

    //add an employee

    public Employee addEmployee(Employee employee){
        return employeeRepository.save(employee);
    }

    //delete employee

    public void deleteEmployee(Long id){
        employeeRepository.deleteById(id);

    }

    //Update an employee
    public Employee updateEmployee(Long id, Employee employee){
        if(employeeRepository.existsById(id)){
            employee.setId(id);
            return employeeRepository.save(employee);
        }
        return null;

    }



}
