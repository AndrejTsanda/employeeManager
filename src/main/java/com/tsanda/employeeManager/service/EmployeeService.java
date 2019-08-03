package com.tsanda.employeeManager.service;

import com.tsanda.employeeManager.dao.EmployeeDAO;
import com.tsanda.employeeManager.domain.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class EmployeeService {

    private final EmployeeDAO employeeDAO;

    @Autowired
    public EmployeeService(EmployeeDAO employeeDAO) {
        this.employeeDAO = employeeDAO;
    }

    public List<Employee> getAllEmployees() {
        return this.employeeDAO.findAll();
    }

    public Optional<Employee> getEmployeeById(Long id) {
        return this.employeeDAO.findById(id);
    }

    public Employee createEmployee(Employee employee) {
        return this.employeeDAO.save(employee);
    }

    public Employee updateEmployee(Employee employee) {
        return this.employeeDAO.save(employee);
    }

    public void deleteEmployeeById(Long id) {
        this.employeeDAO.deleteById(id);
    }
}
