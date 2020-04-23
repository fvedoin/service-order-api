package com.serviceorder.api.serviceorderapi.api.exceptionhandler;

import com.serviceorder.api.serviceorderapi.domain.exception.RuleException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.LocalDateTime;
import java.util.ArrayList;

@ControllerAdvice
public class ApiExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(RuleException.class)
    public ResponseEntity<Object> handleRuleException(RuleException ex, WebRequest request){
        var status = HttpStatus.BAD_REQUEST;
        var problem = new Problem();

        problem.setStatus(status.value());
        problem.setTitle(ex.getMessage());
        problem.setDataTime(LocalDateTime.now());

        return handleExceptionInternal(ex, problem, new HttpHeaders(), status, request);
    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
                                                                  HttpHeaders headers, HttpStatus status, WebRequest request){
        var fields = new ArrayList<Problem.Field>();

        for (ObjectError error: ex.getBindingResult().getAllErrors()){
            String name = ((FieldError) error).getField();
            String message = error.getDefaultMessage();
            fields.add(new Problem.Field(name, message));
        }

        var problem = new Problem();
        problem.setStatus(status.value());
        problem.setTitle("Um ou mais campos estão inválidos. " +
                "Faça o preenchimento correto e tente novamente.");
        problem.setDataTime(LocalDateTime.now());
        problem.setFields(fields);

        return super.handleExceptionInternal(ex, problem, headers, status, request);
    }

}
