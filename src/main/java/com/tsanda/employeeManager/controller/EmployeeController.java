package com.tsanda.employeeManager.controller;

import com.tsanda.employeeManager.domain.Employee;
import com.tsanda.employeeManager.exception.NotFoundException;
import com.tsanda.employeeManager.service.EmployeeService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/employees")
public class EmployeeController {

    private static final Logger log = LoggerFactory.getLogger(EmployeeController.class);
    private final EmployeeService employeeService;

    @Autowired
    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @GetMapping("")
    public List<Employee> getAllEmployees() {
        log.info("process = get-employees!");
        return this.employeeService.getAllEmployees();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Employee> getEmployeeById(@PathVariable("id") Long id) {

        Optional<Employee> employee = (Optional)this.employeeService.getEmployeeById(id);

        log.info("process = get-employe, employee_id = {}", id);
        return employee.map(empl -> ResponseEntity.status(HttpStatus.OK).body(empl))
                .orElseThrow(NotFoundException::new);
    }

    @PostMapping("")
    @ResponseStatus(HttpStatus.CREATED)
    public Employee createEmployee(@RequestBody Employee employee) {
        log.info("process = create-employee, employee = {}", employee);
        return this.employeeService.createEmployee(employee);
    }

    @PutMapping("/{id}")
    public Employee updateEmployeeById(@PathVariable("id") Long id, @RequestBody Employee employee) {
        employee.setId(id);
        log.info("process = update-employee, employee_id = {}", id + " employee = " + employee);
        return this.employeeService.updateEmployee(employee);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteEmployeeById(@PathVariable("id") Long id) {
        log.info("process = delete-employee, employee_id = {}", id);
        this.employeeService.deleteEmployeeById(id);
    }
}
