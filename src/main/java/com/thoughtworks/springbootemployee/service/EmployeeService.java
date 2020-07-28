package com.thoughtworks.springbootemployee.service;

import com.thoughtworks.springbootemployee.entity.Employee;

import java.util.List;

public interface EmployeeService {
    List<Employee> getAllEmployee();

    void addEmployee(Employee employee);

    Employee getEmployeeById(int id);

    List<Employee> getEmployeeByRange(int start, int end);

    List<Employee> getEmployeeByGender(String gender);

    void updateEmployee(int id, Employee employee);

    void deleteEmployee(int id);
}
