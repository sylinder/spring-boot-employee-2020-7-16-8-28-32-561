package com.thoughtworks.springbootemployee.controller;

import com.thoughtworks.springbootemployee.dto.EmployeeRequest;
import com.thoughtworks.springbootemployee.dto.EmployeeResponse;
import com.thoughtworks.springbootemployee.entity.Employee;
import com.thoughtworks.springbootemployee.service.EmployeeService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/employees")
public class EmployeeController {

    private final EmployeeService employeeService;

    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @GetMapping
    public List<Employee> getAllEmployees() {
        return employeeService.getAllEmployees();
    }

    @GetMapping("/{id}")
    public EmployeeResponse getEmployeeById(@PathVariable int id) {
        return employeeService.getEmployeeById(id);
    }

    @GetMapping(params = "unpaged")
    public Page<Employee> getEmployeesByPage(@PageableDefault(size = 2) Pageable pageable, @RequestParam(defaultValue = "true") boolean unpaged) {
        if (unpaged) {
            return employeeService.getAllEmployees(Pageable.unpaged());
        }
        return employeeService.getAllEmployees(pageable);

    }

    @GetMapping(params = "gender")
    public List<Employee> getEmployeesByGender(@RequestParam String gender) {
        return employeeService.getEmployeeByGender(gender);
    }

    @PostMapping
    public void addEmployee(@RequestBody @Valid EmployeeRequest employee) {
        employeeService.addEmployee(employee);
    }

    @PutMapping("/{id}")
    public void updateEmployee(@RequestBody Employee employee) {
        employeeService.updateEmployee(employee);
    }

    @DeleteMapping("/{id}")
    public void deleteEmployeeById(@PathVariable int id){
        employeeService.deleteEmployeeById(id);
    }
}
