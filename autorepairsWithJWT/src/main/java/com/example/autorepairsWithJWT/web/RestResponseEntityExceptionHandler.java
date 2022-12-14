package com.example.autorepairsWithJWT.web;

import com.example.autorepairsWithJWT.exception.NotFoundSparepart;
import com.example.autorepairsWithJWT.exception.NotFoundUserException;
import com.example.autorepairsWithJWT.exception.NotFoundUsernameEmailException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class RestResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {
    @ExceptionHandler(value = {NotFoundUsernameEmailException.class})
    protected ResponseEntity<?> handleExistingUsernameEmailWhenRegisteringNewUser(RuntimeException ex, WebRequest request) {
        return new ResponseEntity(ex.getMessage(), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(value = {NotFoundUserException.class})
    protected ResponseEntity<?> notFoundUserInDatabase(RuntimeException ex, WebRequest request) {
        return new ResponseEntity(ex.getMessage(), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(value = {NotFoundSparepart.class})
    protected ResponseEntity<Object> notFoundSparepart(RuntimeException ex, WebRequest request) {
        return new ResponseEntity(ex.getMessage(), HttpStatus.NOT_FOUND);
    }

    //TODO: these 2 to edit them as they are not ok for Rest API
//    @ExceptionHandler(value = {NotFoundItemToDeleteException.class})
//    protected ResponseEntity<Object> handleConflictDelete(RuntimeException ex, WebRequest request) {
//        String bodyOfResponse =
//                "{" + "\"Conflict reason\" : " + "\"You are trying to delete a non-existing item\"" + "}";
//        return handleExceptionInternal(ex, bodyOfResponse,
//                new HttpHeaders(), HttpStatus.CONFLICT, request);
//    }
}
