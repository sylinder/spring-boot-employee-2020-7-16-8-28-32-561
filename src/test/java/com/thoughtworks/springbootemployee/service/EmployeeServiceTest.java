package com.thoughtworks.springbootemployee.service;

import com.thoughtworks.springbootemployee.dto.EmployeeRequest;
import com.thoughtworks.springbootemployee.entity.Company;
import com.thoughtworks.springbootemployee.entity.Employee;
import com.thoughtworks.springbootemployee.repository.EmployeeRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class EmployeeServiceTest {

    @Mock
    EmployeeRepository employeeRepository;

    @InjectMocks
    EmployeeService employeeService;

    @InjectMocks
    CompanyService companyService;

    /**
     * given: a employeeRequest
     * <p>
     * when: save a employee
     * <p>
     * then: return EmployeeAdd;
     */
    @Test
    public void should_return_employeeAdded_with_id_when_save_employee_given_employee_request() {
        //given
        EmployeeRequest employeeRequest = new EmployeeRequest("zhangsan", 18, "male", 1);
        Company company = new Company();
        company.setName("oocl");
        when(companyService.getCompanyById(company.getId())).thenReturn(company);
        Employee employee = new Employee(1, "zhangsan", 18, "male", company);
        when(employeeRepository.save(any())).thenReturn(employee);
        //when
        Employee employeeAdded = employeeService.addEmployee2(employeeRequest);
        //then
        assertEquals(employeeRequest.getName(), employeeAdded.getName());
    }
}