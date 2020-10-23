package org.redcrosswarriors.security;

import org.redcrosswarriors.model.error.InvalidInput;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import java.util.HashMap;
import java.util.Map;
import java.util.List;
import java.util.stream.Collectors;


@ControllerAdvice
public class InvalidInputHandler extends ResponseEntityExceptionHandler {

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
                                                                  HttpHeaders headers,
                                                                  HttpStatus status, WebRequest request) {
        Map<String, Object> errorResponse = new HashMap<>();
        errorResponse.put("status", status.value());

        List<InvalidInput> errors = ex.getBindingResult()
                .getFieldErrors()
                .stream()
                .map(e -> new InvalidInput(e.getField(), e.getDefaultMessage()))
                .collect(Collectors.toList());

        errorResponse.put("errors", errors);

        return new ResponseEntity<>(errorResponse, status);
    }
}
