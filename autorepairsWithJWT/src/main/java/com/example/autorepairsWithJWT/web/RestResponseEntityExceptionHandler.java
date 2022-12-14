package com.example.autorepairsWithJWT.web;

import com.example.autorepairsWithJWT.exception.ConflictSparepartException;
import com.example.autorepairsWithJWT.exception.ConflictUsernameEmailException;
import com.example.autorepairsWithJWT.exception.NotFoundSparepart;
import com.example.autorepairsWithJWT.exception.NotFoundUserException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class RestResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {
    @ExceptionHandler(value = {ConflictUsernameEmailException.class})
    protected ResponseEntity<?> handleExistingUsernameEmailWhenRegisteringNewUser(RuntimeException ex, WebRequest request) {
        return new ResponseEntity(ex.getMessage(), HttpStatus.CONFLICT);
    }

    @ExceptionHandler(value = {ConflictSparepartException.class})
    protected ResponseEntity<?> handleConflictsWithSpareparts(RuntimeException ex) {
        return new ResponseEntity(ex.getMessage(), HttpStatus.CONFLICT);
    }

    @ExceptionHandler(value = {NotFoundUserException.class})
    protected ResponseEntity<?> handleNotFoundUserInDatabase(RuntimeException ex) {
        return new ResponseEntity(ex.getMessage(), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(value = {NotFoundSparepart.class})
    protected ResponseEntity<Object> handleNotFoundSparepartInDatabase(RuntimeException ex) {
        return new ResponseEntity(ex.getMessage(), HttpStatus.NOT_FOUND);
    }
}
