package com.hirechart.hiredin.exception;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.hirechart.hiredin.Dto.ResponseStructure;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ResponseStructure<Map<String, String>>> handleValidationExceptions(MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();
        for (FieldError error : ex.getBindingResult().getFieldErrors()) {
            errors.put(error.getField(), error.getDefaultMessage());
        }
        ResponseStructure<Map<String, String>> response = new ResponseStructure<>();
        response.setStatuscode(HttpStatus.BAD_REQUEST.value());
        response.setMessage("Validation failed for one or more fields");
        response.setData(errors);
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(InvalidCandidateDataException.class)
    public ResponseEntity<ResponseStructure<String>> handleInvalidCandidateData(InvalidCandidateDataException ex) {
        ResponseStructure<String> response = new ResponseStructure<>();
        response.setStatuscode(HttpStatus.BAD_REQUEST.value());
        response.setMessage(ex.getMessage());
        response.setData("Validation Error");
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(CandidateNotFound.class)
    public ResponseEntity<ResponseStructure<String>> handleCandidateNotFound(CandidateNotFound ex) {
        ResponseStructure<String> response = new ResponseStructure<>();
        response.setStatuscode(HttpStatus.NOT_FOUND.value());
        response.setMessage(ex.getMessage());
        response.setData("Candidate Not Found");
        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(CompanyNotFoundException.class)
    public ResponseEntity<ResponseStructure<String>> handleCompanyNotFound(CompanyNotFoundException ex) {
        ResponseStructure<String> response = new ResponseStructure<>();
        response.setStatuscode(HttpStatus.NOT_FOUND.value());
        response.setMessage(ex.getMessage());
        response.setData("Company Not Found");
        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    }
}
