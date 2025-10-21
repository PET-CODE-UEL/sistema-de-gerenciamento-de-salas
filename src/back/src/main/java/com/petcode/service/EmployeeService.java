package com.petcode.service;

import com.petcode.entity.Employee;
import com.petcode.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EmployeeService {
    
    @Autowired
    private EmployeeRepository employeeRepository;
    
    public Employee createEmployee(String name, String email, String phone) {
        Employee employee = new Employee(name, email, phone);
        return employeeRepository.save(employee);
    }
    
    public Employee getById(Long id) {
        Optional<Employee> funcionario = employeeRepository.findById(id);
        return funcionario.orElse(null);
    }
    
    public List<Employee> getAll() {
        return employeeRepository.findAll();
    }
    
    public Employee updateEmployee(Employee employee) {
        return employeeRepository.save(employee);
    }
    
    public void deleteEmployee(Long id) {
        employeeRepository.deleteById(id);
    }
    
    public long employeeCount() {
        return employeeRepository.count();
    }
}
