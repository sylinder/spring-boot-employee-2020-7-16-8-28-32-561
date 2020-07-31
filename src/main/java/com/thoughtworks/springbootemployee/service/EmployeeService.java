package com.thoughtworks.springbootemployee.service;

import com.thoughtworks.springbootemployee.dto.EmployeeRequest;
import com.thoughtworks.springbootemployee.dto.EmployeeResponse;
import com.thoughtworks.springbootemployee.entity.Company;
import com.thoughtworks.springbootemployee.entity.Employee;
import com.thoughtworks.springbootemployee.exception.NoSuchCompanyException;
import com.thoughtworks.springbootemployee.exception.NoSuchEmployeeException;
import com.thoughtworks.springbootemployee.repository.CompanyRepository;
import com.thoughtworks.springbootemployee.repository.EmployeeRepository;
import com.thoughtworks.springbootemployee.util.EmployeeToEmployeeResponse;
import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class EmployeeService {

    private EmployeeRepository employeeRepository;
    private final CompanyRepository companyRepository;

    public EmployeeService(EmployeeRepository employeeRepository, CompanyRepository companyRepository) {
        this.employeeRepository = employeeRepository;
        this.companyRepository = companyRepository;
    }

    public List<EmployeeResponse> getAllEmployees() {
       return employeeRepository.findAll().stream().
                map(EmployeeToEmployeeResponse::transferEmployeeToEmployeeResponse)
                .collect(Collectors.toList());
    }


    public EmployeeResponse getEmployeeById(int id) {
        Optional<Employee> optionalEmployee = employeeRepository.findById(id);
        if (optionalEmployee.isPresent()) {
            EmployeeResponse employeeResponse = new EmployeeResponse();
            BeanUtils.copyProperties(optionalEmployee.get(), employeeResponse);
            employeeResponse.setCompanyName(optionalEmployee.get().getCompany().getName());
            return employeeResponse;
        }
        throw new NoSuchEmployeeException();
    }

    public Page<Employee> getAllEmployees(Pageable pageable) {
        return employeeRepository.findAll(pageable);
    }

    public List<EmployeeResponse> getEmployeeByGender(String gender) {
        return null;
    }

    public void updateEmployee(Employee employee) {
        employeeRepository.save(employee);
    }

    public void deleteEmployeeById(int id) {
        employeeRepository.deleteById(id);
    }

    public EmployeeResponse addEmployee(EmployeeRequest employeeRequest) {
        Optional<Company> company = companyRepository.findById(employeeRequest.getCompanyId());
        if (company.isPresent()) {
            Employee employee = new Employee();
            employee.setAge(employeeRequest.getAge());
            employee.setName(employeeRequest.getName());
            employee.setGender(employeeRequest.getGender());
            employee.setCompany(company.get());
            Employee employeeAdded = employeeRepository.save(employee);
            EmployeeResponse employeeResponse = new EmployeeResponse();
            BeanUtils.copyProperties(employeeAdded, employeeResponse);
            employeeResponse.setCompanyName(company.get().getName());
            return employeeResponse;
        }
        throw new NoSuchCompanyException();
    }
}
