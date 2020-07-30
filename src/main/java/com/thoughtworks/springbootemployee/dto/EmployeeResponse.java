package com.thoughtworks.springbootemployee.dto;

public class EmployeeResponse {
    private String name;
    private int age;
    private String gender;
    private String companyName;

    public EmployeeResponse(String name, int age, String gender, String companyName) {
        this.name = name;
        this.age = age;
        this.gender = gender;
        this.companyName = companyName;
    }

    public EmployeeResponse() {
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

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }
}
