package com.thoughtworks.springbootemployee.service;

import com.thoughtworks.springbootemployee.entity.Company;
import com.thoughtworks.springbootemployee.entity.Employee;
import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.List;

@Service
public class CompanyServiceImpl implements  CompanyService {

    private List<Company> companies;

    public CompanyServiceImpl() {
        this.companies = new LinkedList<>();
    }

    @Override
    public Company getCompanyById(int id) {
        for (Company company : companies) {
            if (company.getId() == id) {
                return company;
            }
        }
        return null;
    }

    @Override
    public List<Company> getAllCompanies() {
        return companies;
    }

    @Override
    public void addCompany(Company company) {
        companies.add(company);
    }
}
