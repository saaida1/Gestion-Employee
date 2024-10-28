package com.DevSecOps.SpringProject.service;

import com.DevSecOps.SpringProject.model.Employee;
import com.DevSecOps.SpringProject.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Locale;
import java.util.Optional;

@Service
public class EmployeeService {

    private final EmployeeRepository employeeRepository;
    private final MessageSource messageSource;

    @Autowired
    public EmployeeService(EmployeeRepository employeeRepository, MessageSource messageSource) {
        this.employeeRepository = employeeRepository;
        this.messageSource = messageSource;
    }

    //Get all employees
    public List<Employee> GetAllEmployees(){
        return employeeRepository.findAll();
    }

    //Get employee by Id avec message
    public Employee getEmployeeById(Long id) {
        Locale locale = LocaleContextHolder.getLocale();
        return employeeRepository.findById(id)
                .orElseThrow(() -> new RuntimeException(
                        messageSource.getMessage("employee.not.found", null, locale)
                ));
    }

    //Add employee
    public Employee addEmployee(Employee employee) {
        Locale locale = LocaleContextHolder.getLocale();

        if (employeeRepository.findByEmail(employee.getEmail()).isPresent()) {
            throw new RuntimeException(
                    messageSource.getMessage("employee.email.exists", null, locale)
            );
        }

        employeeRepository.save(employee);
        String successMessage = messageSource.getMessage("employee.added", null, locale);
        System.out.println(successMessage);
        return employee;
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