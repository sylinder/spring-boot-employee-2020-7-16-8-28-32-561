package com.thoughtworks.springbootemployee.service;

import com.thoughtworks.springbootemployee.entity.Company;
import com.thoughtworks.springbootemployee.entity.Employee;
import com.thoughtworks.springbootemployee.exception.NoCompanyFoundException;
import com.thoughtworks.springbootemployee.exception.NoEmployFoundException;
import com.thoughtworks.springbootemployee.repository.CompanyRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@Service
public class CompanyService {
    private final CompanyRepository companyRepository;

    public CompanyService(CompanyRepository companyRepository) {
        this.companyRepository = companyRepository;
    }

    public Page<Company> getAllCompany(Pageable pageable){
        return companyRepository.findAll(pageable);
    }

    public List<Company> getAllCompany() {
        return companyRepository.findAll();
    }

    public List<Employee> getAllEmployeeByCompanyId(int id) throws NoCompanyFoundException {
        return companyRepository.findById(id).orElseThrow(NoCompanyFoundException:: new)
                .getEmployees();
    }

    public void deleteCompanyById(int id)
    {
        companyRepository.deleteById(id);
    }

    public Company getCompanyById(int id) throws NoCompanyFoundException {
        return companyRepository.findById(id).orElseThrow(() -> new NoCompanyFoundException());
    }

    public void addCompany(Company company) {
        companyRepository.save(company);
    }

    public void updateCompany(Company company) {
        companyRepository.save(company);
    }
}
