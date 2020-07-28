package com.thoughtworks.springbootemployee.service;

import com.thoughtworks.springbootemployee.entity.Company;
import com.thoughtworks.springbootemployee.entity.Employee;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

@Service
public class CompanyServiceImpl implements  CompanyService {

    private List<Company> companies;

    public CompanyServiceImpl() {
        this.companies = new ArrayList<>();
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

    @Override
    public List<Employee> getAllEmployeesByCompanyId(int id) {
        Company queryCompany = null;
        for (Company company : companies) {
            if (company.getId() == id) {
                queryCompany = company;
            }
        }
        if (queryCompany == null)
            return null;
        return queryCompany.getEmployees();
    }

    @Override
    public List<Company> getCompaniesByRange(int start, int end) {
        List<Company> returnCompanies = new ArrayList<>();
        for (int index = start - 1; index < companies.size() && index < end - 1; index++) {
            returnCompanies.add(companies.get(index));
        }
        return returnCompanies;
    }

    @Override
    public void updateCompany(int id, Company company) {
        for (Company modifyCompany : companies) {
            if (modifyCompany.getId() == id) {
                companies.remove(modifyCompany);
                companies.add(company);
                return;
            }
        }
    }
}
