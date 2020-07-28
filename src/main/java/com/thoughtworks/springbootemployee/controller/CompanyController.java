package com.thoughtworks.springbootemployee.controller;

import com.thoughtworks.springbootemployee.entity.Company;
import com.thoughtworks.springbootemployee.entity.Employee;
import com.thoughtworks.springbootemployee.service.CompanyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class CompanyController {

    @Autowired
    CompanyService companyService;

    @GetMapping("/companies")
    public List<Company> getAllCompanies() {
        return companyService.getAllCompanies();
    }

    @GetMapping("/companies/{id}")
    public Company getCompanyById(@PathVariable int id) {
        return companyService.getCompanyById(id);
    }

    @PostMapping("/companies")
    public void addCompany(@RequestBody Company company) {
        companyService.addCompany(company);
    }

    @GetMapping("/companies/{id}/employees")
    public List<Employee> getAllEmployeesByCompanyId(@PathVariable int id) {
        return companyService.getAllEmployeesByCompanyId(id);
    }
}
