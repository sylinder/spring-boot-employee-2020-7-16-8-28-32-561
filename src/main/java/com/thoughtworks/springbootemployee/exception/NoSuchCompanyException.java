package com.thoughtworks.springbootemployee.exception;

public class NoSuchCompanyException extends RuntimeException {
    public NoSuchCompanyException() {
        super("company not found");
    }
}
