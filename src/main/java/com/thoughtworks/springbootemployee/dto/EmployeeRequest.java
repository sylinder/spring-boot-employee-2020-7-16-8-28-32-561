package com.thoughtworks.springbootemployee.dto;


import javax.validation.constraints.Digits;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Positive;

public class EmployeeRequest {
    @NotBlank
    private String name;
    @Positive
    private int age;
    @NotBlank
    private String gender;
    private int companyId;

    public EmployeeRequest(String name, int age, String gender, int companyId) {
        this.name = name;
        this.age = age;
        this.gender = gender;
        this.companyId = companyId;
    }

    public EmployeeRequest() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public int getCompanyId() {
        return companyId;
    }

    public void setCompanyId(int companyId) {
        this.companyId = companyId;
    }
}
