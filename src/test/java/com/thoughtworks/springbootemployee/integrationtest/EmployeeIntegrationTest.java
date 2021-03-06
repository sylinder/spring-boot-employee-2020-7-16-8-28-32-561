package com.thoughtworks.springbootemployee.integrationtest;

import com.thoughtworks.springbootemployee.dto.EmployeeResponse;
import com.thoughtworks.springbootemployee.entity.Company;
import com.thoughtworks.springbootemployee.entity.Employee;
import com.thoughtworks.springbootemployee.repository.CompanyRepository;
import com.thoughtworks.springbootemployee.repository.EmployeeRepository;
import com.thoughtworks.springbootemployee.service.EmployeeService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@SpringBootTest
@AutoConfigureMockMvc
public class EmployeeIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    private CompanyRepository companyRepository;

    @Autowired
    private EmployeeService employeeService;

    @BeforeEach
    void tearDown() {
        employeeRepository.deleteAll();
        employeeRepository.flush();
    }

    @Test
    void should_return_ok_when_get_employees() throws Exception {
        //when - then
        mockMvc.perform(get("/employees")).andExpect(status().isOk());
    }

    @Test
    void should_return_xiaoming_when_get_employee_by_id_given_employee_id() throws Exception {
        //given
        Employee employee = new Employee();
        employee.setName("xiaoming");
        Company company = new Company();
        company.setName("oocl");
        Company companySaved = companyRepository.save(company); //for company id not null
        employee.setCompany(companySaved);
        Employee employeeSaved = employeeRepository.save(employee);
        int employeeId = employeeSaved.getId();

        //when
        mockMvc.perform(get(String.format("/employees/%d", employeeId)))
                .andExpect(status().isOk());

        //then
        EmployeeResponse returningEmployee = employeeService.getEmployeeById(employeeId);
        assertEquals("xiaoming", returningEmployee.getName());
    }

    @Test
    void should_return_2_when_get_employees_by_gender_male_given_2_male_and_1_female() throws Exception {
        //given
        Company company = new Company();
        company.setName("oocl");
        Company companySaved = companyRepository.save(company);
        List<Employee> employees = new ArrayList<>();
        for (int index = 0; index < 3; index++) {
            Employee employee = new Employee();
            employee.setName("aha");
            employee.setCompany(company);
            employee.setGender("male");
            employees.add(employee);
        }
        employees.get(2).setGender("female");
        employeeRepository.saveAll(employees);

        //when
        String gender = "male";
        mockMvc.perform(get(String.format("/employees?gender=%s", "gender")))
                .andExpect(status().isOk());

        //then
        List<EmployeeResponse> employeeResponseList = employeeService.getEmployeeByGender(gender);
        assertEquals(2, employeeResponseList.size());

    }

    @Test
    void should_return_1_employee_when_add_employee_given_1_employee() throws Exception {
        //given
        Company company = new Company();
        company.setName("oocl");
        Company companySaved = companyRepository.save(company);
        int companyId = companySaved.getId();
        String employeePayload = String.format("{\"name\": \"xiaoming\"," +
                "\"age\": 18, \"gender\": \"male\", \"companyId\": %d}", companyId);

        //when
        mockMvc.perform(post("/employees")
                .contentType(MediaType.APPLICATION_JSON)
                .content(employeePayload))
                .andExpect(status().isOk());

        //then
        List<Employee> employees = employeeRepository.findAll();
        assertEquals(1, employees.size());
    }

    @Test
    void should_return_0_employee_when_delete_employee_given_id() throws Exception {
        //given
        Company company = new Company();
        company.setName("oocl");
        Company companySaved = companyRepository.save(company);
        Employee employee = new Employee(1, "xiaoming", 18, "male", companySaved);
        Employee employeeSaved = employeeRepository.save(employee);
        int employeeId = employeeSaved.getId();

        //when
        mockMvc.perform(delete(String.format("/employees/%d", employeeId))).andExpect(status().isOk());

        //then
        List<Employee> employeeList = employeeRepository.findAll();
        assertEquals(0, employeeList.size());
    }

    @Test
    void should_return_new_employee_when_update_employee_by_id_given_a_old_employee() throws Exception {
        //given
        Company company = new Company();
        company.setName("oocl");
        Company companySaved = companyRepository.save(company);
        int companyId = companySaved.getId();
        Employee employee = new Employee();
        employee.setName("xiaoming");
        employee.setCompany(companySaved);
        Employee employeeSaved = employeeRepository.save(employee);
        int employeeId = employeeSaved.getId();
        String employeePayload = String.format("{\"id\": %d,\"name\": \"xiaohong\", \"company\": {\"id\": %d}}", employeeId, companyId);

        //when
        mockMvc.perform(put(String.format("/employees/%d", employeeId))
                .contentType(MediaType.APPLICATION_JSON)
                .content(employeePayload))
                .andExpect(status().isOk());

        //then
        Employee returningEmployee = employeeRepository.findById(employeeId).get();
        assertEquals("xiaohong", returningEmployee.getName());

    }

}
