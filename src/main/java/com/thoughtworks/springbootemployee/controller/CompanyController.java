package com.thoughtworks.springbootemployee.controller;

import com.thoughtworks.springbootemployee.entity.Company;
import com.thoughtworks.springbootemployee.entity.Employee;
import com.thoughtworks.springbootemployee.exception.NoCompanyFoundException;
import com.thoughtworks.springbootemployee.service.CompanyService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/companies")
public class CompanyController {

    private final CompanyService companyService;

    public CompanyController(CompanyService companyService) {
        this.companyService = companyService;
    }

    @GetMapping
    public List<Company> getAllCompany(){
        return companyService.getAllCompany();
    }

    @GetMapping("/{id}/employees")
    public List<Employee> getEmployeesByCompanyId(@PathVariable int id) throws NoCompanyFoundException {
        return companyService.getAllEmployeeByCompanyId(id);
    }

    @GetMapping("/{id}")
    public Company getCompanyById(@PathVariable int id) throws NoCompanyFoundException {
        return companyService.getCompanyById(id);
    }

    @DeleteMapping("/{id}")
    public void deleteCompanyById(@PathVariable int id){
        companyService.deleteCompanyById(id);
    }

    @GetMapping(params = "unpaged")
    public Page<Company> getAllEmployees(@PageableDefault(size = 2) Pageable pageable, @RequestParam(defaultValue = "false") boolean unpaged) {
        if(unpaged){
            return companyService.getAllCompany(Pageable.unpaged());
        }
        return  companyService.getAllCompany(pageable);
    }

    @PostMapping
    public void addCompany(@RequestBody Company company) {
        companyService.addCompany(company);
    }

    @PutMapping("/{id}")
    public void updateCompany(@RequestBody Company company) {
        companyService.updateCompany(company);
    }
}
