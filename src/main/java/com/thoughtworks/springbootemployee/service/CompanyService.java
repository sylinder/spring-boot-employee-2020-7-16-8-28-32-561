package com.thoughtworks.springbootemployee.service;

import com.thoughtworks.springbootemployee.entity.Company;
import com.thoughtworks.springbootemployee.entity.Employee;
import com.thoughtworks.springbootemployee.exception.NoSuchCompanyException;
import com.thoughtworks.springbootemployee.repository.CompanyRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CompanyService {

    private final CompanyRepository companyRepository;

    public CompanyService(CompanyRepository companyRepository) {
        this.companyRepository = companyRepository;
    }

    public List<Company> getAllCompanies() {
        return companyRepository.findAll();
    }

    public Company getCompanyById(int id) {
        return companyRepository.findById(id).orElseThrow(() -> new NoSuchCompanyException());
    }

    public List<Employee> getEmployeesByCompanyId(int id) {
        return companyRepository.findById(id)
                .orElseThrow(NoSuchCompanyException::new)
                .getEmployees();
    }

    public Page<Company> getAllCompanies(Pageable pageable) {
        return companyRepository.findAll(pageable);
    }

    public void addCompany(Company company) {
        companyRepository.save(company);
    }

    public void updateCompany(Company company) {
//        companyRepository.deleteById(id);
        companyRepository.save(company);
    }
}
