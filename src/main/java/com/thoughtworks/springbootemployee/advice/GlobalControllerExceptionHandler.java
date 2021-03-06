package com.thoughtworks.springbootemployee.advice;

import com.thoughtworks.springbootemployee.exception.ExceptionStatus;
import com.thoughtworks.springbootemployee.exception.NoSuchCompanyException;
import com.thoughtworks.springbootemployee.exception.NoSuchEmployeeException;
import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

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

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler({MethodArgumentNotValidException.class})
    @ResponseBody
    public List<String> handleMethodArgumentNotValidException(MethodArgumentNotValidException exception){

        return exception.getBindingResult().getFieldErrors().stream()
                .map(e -> e.getField()+":"+e.getDefaultMessage()).collect(Collectors.toList());
    }


}
