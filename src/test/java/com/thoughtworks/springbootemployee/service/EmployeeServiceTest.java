package com.thoughtworks.springbootemployee.service;

import com.thoughtworks.springbootemployee.dto.EmployeeRequest;
import com.thoughtworks.springbootemployee.dto.EmployeeResponse;
import com.thoughtworks.springbootemployee.entity.Company;
import com.thoughtworks.springbootemployee.entity.Employee;
import com.thoughtworks.springbootemployee.exception.NoSuchCompanyException;
import com.thoughtworks.springbootemployee.repository.CompanyRepository;
import com.thoughtworks.springbootemployee.repository.EmployeeRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.BeanUtils;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class EmployeeServiceTest {

    @Mock
    EmployeeRepository employeeRepository;

    @InjectMocks
    EmployeeService employeeService;

    @Mock
    CompanyRepository companyRepository;

    /**
     * given: a employeeRequest
     * <p>
     * when: save a employee
     * <p>
     * then: return EmployeeResponse;
     */
    @Test
    public void should_return_employee_response_with_id_when_save_employee_given_employee_request() {
        //given
        EmployeeRequest employeeRequest = new EmployeeRequest("zhangsan", 18, "male", 1);
        Company company = new Company();
        company.setName("oocl");
        company.setId(1);
        when(companyRepository.findById(any())).thenReturn(Optional.of(company));
        Employee employee = new Employee(1, "zhangsan", 18, "male", company);
        when(employeeRepository.save(any())).thenReturn(employee);
        //when
        EmployeeResponse employeeResponse = employeeService.addEmployee(employeeRequest);
        //then
        assertEquals(employeeRequest.getName(), employeeResponse.getName());
    }


    @Test
    void should_throw_no_such_company_exception_when_add_employee_given_request_employee_with_no_exit_company_id() {
        //given
        EmployeeRequest employeeRequest = new EmployeeRequest("zhangsan", 18, "male", 1);
        when(companyRepository.findById(any())).thenReturn(Optional.empty());
        //when
        Throwable throwable = assertThrows(NoSuchCompanyException.class, () -> employeeService.addEmployee(employeeRequest));

        //then
        assertEquals("company not found",throwable.getMessage());
    }

    @Test
    void should_return_employee_response_when_query_employee_by_id_given_exist_id() {
        //given
        int id = 1;
        String name = "caps";
        Employee employee = new Employee();
        employee.setId(id);
        employee.setName(name);
        Company company = new Company();
        company.setName("caps");
        employee.setCompany(company);
        when(employeeRepository.findById(Mockito.anyInt())).thenReturn(Optional.of(employee));
        //when
        EmployeeResponse employeeResponse = employeeService.getEmployeeById(id);
        //then
        assertEquals(name, employeeResponse.getName());
    }
}