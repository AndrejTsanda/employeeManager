package com.tsanda.employeeManager.controller;

import com.tsanda.employeeManager.domain.Employee;
import com.tsanda.employeeManager.exception.NotFoundException;
import com.tsanda.employeeManager.service.EmployeeService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Api(value = "EmployeeController", description = "REST APIs related to Employee Entity.")
@RestController
@RequestMapping("/employees")
public class EmployeeController {

    private static final Logger log = LoggerFactory.getLogger(EmployeeController.class);
    private final EmployeeService employeeService;

    @Autowired
    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @ApiOperation(value = "Get all Employees")
    @GetMapping("")
    public List<Employee> getAllEmployees() {
        log.info("process = get-employees!");
        return this.employeeService.getAllEmployees();
    }

    @ApiOperation(value = "Get Employee By Id")
    @GetMapping("/{id}")
    public ResponseEntity<Employee> getEmployeeById(@PathVariable("id") Long id) {

        Optional<Employee> employee = (Optional)this.employeeService.getEmployeeById(id);

        log.info("process = get-employe, employee_id = {}", id);
        return employee.map(empl -> ResponseEntity.status(HttpStatus.OK).body(empl))
                .orElseThrow(NotFoundException::new);
    }

    @ApiOperation(value = "Create Employee")
    @PostMapping("")
    @ResponseStatus(HttpStatus.CREATED)
    public Employee createEmployee(@RequestBody Employee employee) {
        log.info("process = create-employee, employee = {}", employee);
        return this.employeeService.createEmployee(employee);
    }

    @ApiOperation(value = "Update Employee By Id")
    @PutMapping("/{id}")
    public Employee updateEmployeeById(@PathVariable("id") Long id, @RequestBody Employee employee) {
        employee.setId(id);
        log.info("process = update-employee, employee_id = {}", id + " employee = " + employee);
        return this.employeeService.updateEmployee(employee);
    }

    @ApiOperation(value = "Delete Employee By Id")
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteEmployeeById(@PathVariable("id") Long id) {
        log.info("process = delete-employee, employee_id = {}", id);
        this.employeeService.deleteEmployeeById(id);
    }
}
