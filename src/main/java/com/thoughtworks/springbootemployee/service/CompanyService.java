package com.thoughtworks.springbootemployee.service;

import com.thoughtworks.springbootemployee.dto.EmployeeResponse;
import com.thoughtworks.springbootemployee.entity.Company;
import com.thoughtworks.springbootemployee.entity.Employee;
import com.thoughtworks.springbootemployee.exception.NoSuchCompanyException;
import com.thoughtworks.springbootemployee.repository.CompanyRepository;
import com.thoughtworks.springbootemployee.util.EmployeeToEmployeeResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

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
        return companyRepository.findById(id).orElseThrow(NoSuchCompanyException::new);
    }

    public List<EmployeeResponse> getEmployeesByCompanyId(int id) {
        return companyRepository.findById(id)
                .orElseThrow(NoSuchCompanyException::new)
                .getEmployees()
                .stream()
                .map(EmployeeToEmployeeResponse::transferEmployeeToEmployeeResponse)
                .collect(Collectors.toList());
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

    public void deleteCompanyById(int id) {
        companyRepository.deleteById(id);
    }
}
