package com.thoughtworks.springbootemployee.service;

import com.thoughtworks.springbootemployee.entity.Employee;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class EmployeeServiceImpl implements  EmployeeService {
    private List<Employee> employees;

    public EmployeeServiceImpl() {
        this.employees = new ArrayList<>();
    }

    @Override
    public List<Employee> getAllEmployee() {
        return employees;
    }

    @Override
    public void addEmployee(Employee employee) {
        employees.add(employee);
    }

    @Override
    public Employee getEmployeeById(int id) {
        for (Employee employee : employees) {
            if (employee.getId() == id) {
                return employee;
            }
        }
        return null;
    }

    @Override
    public List<Employee> getEmployeeByRange(int start, int end) {
        List<Employee> returningEmployees = new ArrayList<>();
        for (int index = start - 0; index < employees.size() && index < end - 1; index++) {
            returningEmployees.add(employees.get(index));
        }
        return returningEmployees;
    }

    @Override
    public List<Employee> getEmployeeByGender(String gender) {
        List<Employee> returningEmployees = new ArrayList<>();
        for (Employee employee : employees) {
            if (employee.getGender().equals(gender)) {
                returningEmployees.add(employee);
            }
        }
        return returningEmployees;
    }

    @Override
    public void updateEmployee(int id, Employee employee) {
        for (Employee innerEmployee : employees) {
            if (innerEmployee.getId() == id) {
                employees.remove(innerEmployee);
                employees.add(employee);
                return ;
            }
        }
    }
}
