package com.thoughtworks.springbootemployee.service;

import com.thoughtworks.springbootemployee.entity.Company;
import com.thoughtworks.springbootemployee.entity.Employee;

import java.util.List;

public interface CompanyService {
    Company getCompanyById(int id);

    List<Company> getAllCompanies();

    void addCompany(Company company);

    List<Employee> getAllEmployeesByCompanyId(int id);

    List<Company> getCompaniesByRange(int start, int end);
}
