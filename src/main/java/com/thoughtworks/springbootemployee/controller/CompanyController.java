package com.thoughtworks.springbootemployee.controller;

import com.thoughtworks.springbootemployee.dto.EmployeeResponse;
import com.thoughtworks.springbootemployee.entity.Company;
import com.thoughtworks.springbootemployee.entity.Employee;
import com.thoughtworks.springbootemployee.service.CompanyService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/companies")
public class CompanyController {

    private final CompanyService companyService;

    public CompanyController(CompanyService companyService) {
        this.companyService = companyService;
    }

    @GetMapping()
    public List<Company> getAllCompanies() {
        return companyService.getAllCompanies();
    }

    @GetMapping("/{id}")
    public Company getCompanyById(@PathVariable int id) {
        return companyService.getCompanyById(id);
    }

    @GetMapping("/{id}/employees")
    public List<EmployeeResponse> getEmployeesByCompanyId(@PathVariable int id) {
        return companyService.getEmployeesByCompanyId(id);
    }

    @GetMapping(params = "unpaged")
    public Page<Company> getCompaniesByRange(@PageableDefault(size = 2) Pageable pageable, @RequestParam(defaultValue = "true") boolean unpaged) {
        if (unpaged) {
            return companyService.getAllCompanies(Pageable.unpaged());
        }
        return companyService.getAllCompanies(pageable);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void addCompany(@RequestBody Company company) {
        companyService.addCompany(company);
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void updateCompany(@RequestBody Company company) {
        companyService.updateCompany(company);
    }

    @DeleteMapping("/{id}")
    public void deleteCompanyById(@PathVariable int id) {
        companyService.deleteCompanyById(id);
    }
}
