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


//    @GetMapping("/companies")
//    public List<Company> getCompaniesInRange(@RequestParam("page") int page, @RequestParam int pageSize) {
//        return companyService.getCompaniesByRange(page, pageSize);
//    }

    @PutMapping("/companies/{id}")
    public void updateCompany(@PathVariable int id, @RequestBody Company company) {
        companyService.updateCompany(id, company);
    }

    @DeleteMapping("/companies/{id}")
    public void deleteCompany(@PathVariable int id) {
        companyService.deleteCompany(id);
    }
}
