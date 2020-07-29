package com.thoughtworks.springbootemployee.controller;

import com.thoughtworks.springbootemployee.entity.Employee;
import com.thoughtworks.springbootemployee.exception.NoEmployFoundException;
import com.thoughtworks.springbootemployee.service.EmployeeService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.*;

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
    public Employee getEmployeeById(@PathVariable int id) throws NoEmployFoundException {
        return employeeService.getEmployeeById(id);
    }

    @GetMapping(params = "unpaged")
    public Page<Employee> getAllEmployees(@PageableDefault(size = 2)Pageable pageable,@RequestParam(defaultValue = "false") boolean unpaged) {
        if(unpaged){
            return employeeService.getAllEmployees(Pageable.unpaged());
        }
        return  employeeService.getAllEmployees(pageable);
    }

    @GetMapping(params ="gender")
    public List<Employee> getEmployeeByGender(@RequestParam("gender") String gender){
        return employeeService.getEmployeeByGender(gender);
    }

    @PostMapping
    public void addEmployee(@RequestBody Employee employee){
        employeeService.addEmployee(employee);
    }

    @PutMapping("/{id}")
    public void updateEmployee(@RequestBody Employee employee ){
        employeeService.updateEmployee(employee);
    }

    @DeleteMapping("/{id}")
    public void deleteEmployee(@PathVariable int id){
        employeeService.deleteEmployee(id);
    }

}
