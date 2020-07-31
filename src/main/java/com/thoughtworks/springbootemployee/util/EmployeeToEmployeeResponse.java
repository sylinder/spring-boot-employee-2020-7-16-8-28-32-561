package com.thoughtworks.springbootemployee.util;

import com.thoughtworks.springbootemployee.dto.EmployeeResponse;
import com.thoughtworks.springbootemployee.entity.Employee;
import org.springframework.beans.BeanUtils;

public class EmployeeToEmployeeResponse {

    public static EmployeeResponse transferEmployeeToEmployeeResponse(Employee employee){
        EmployeeResponse employeeResponse = new EmployeeResponse();
        BeanUtils.copyProperties(employee,employeeResponse);
        return employeeResponse;
    }
}
