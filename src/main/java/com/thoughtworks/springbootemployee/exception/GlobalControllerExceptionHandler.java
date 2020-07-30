package com.thoughtworks.springbootemployee.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@ControllerAdvice
public class GlobalControllerExceptionHandler {

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(NoSuchCompanyException.class)
    @ResponseBody
    public ExceptionStatus handleNotFoundCompany() {
        ExceptionStatus exceptionStatus = new ExceptionStatus();
        exceptionStatus.setErrorMessage("No Such Company.");
        return exceptionStatus;
    }

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(NoSuchEmployeeException.class)
    @ResponseBody
    public ExceptionStatus handleNotFoundEmployee() {
        ExceptionStatus exceptionStatus = new ExceptionStatus();
        exceptionStatus.setErrorMessage("No Such Employee.");
        return exceptionStatus;
    }

//    public ExceptionStatus handleOthers() {
//        ExceptionStatus exceptionStatus = new ExceptionStatus();
//        exceptionStatus.setStatus();
//    }
}
