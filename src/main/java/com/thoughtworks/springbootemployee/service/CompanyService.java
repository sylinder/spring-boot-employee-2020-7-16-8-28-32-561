package com.thoughtworks.springbootemployee.service;

import com.thoughtworks.springbootemployee.entity.Company;

import java.util.List;

public interface CompanyService {
    Company getCompanyById(int id);

    List<Company> getAllCompanies();
}
