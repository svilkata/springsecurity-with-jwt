package com.example.autorepairsWithJWT.web;

import com.example.autorepairsWithJWT.exception.NotFoundItemToDeleteException;
import com.example.autorepairsWithJWT.exception.NotFoundItemToUpdateException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class RestResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {

//    @ExceptionHandler(value
//            = {IllegalArgumentException.class, IllegalStateException.class})
//    protected ResponseEntity<Object> handleConflict(RuntimeException ex, WebRequest request) {
//        String bodyOfResponse =  "This should be application specific";
//        return handleExceptionInternal(ex, bodyOfResponse,
//                new HttpHeaders(), HttpStatus.CONFLICT, request);
//    }

    @ExceptionHandler(value
            = {NotFoundItemToUpdateException.class})
    protected ResponseEntity<Object> handleConflictUpdate(RuntimeException ex, WebRequest request) {
        String bodyOfResponse =
                "{" + "\"Conflict reason\" : "  + "\"You are trying to update a non-existing item\"" + "}";
        return handleExceptionInternal(ex, bodyOfResponse,
                new HttpHeaders(), HttpStatus.CONFLICT, request);
    }

    @ExceptionHandler(value
            = {NotFoundItemToDeleteException.class})
    protected ResponseEntity<Object> handleConflictDelete(RuntimeException ex, WebRequest request) {
        String bodyOfResponse =
                "{" + "\"Conflict reason\" : "  + "\"You are trying to delete a non-existing item\"" + "}";
        return handleExceptionInternal(ex, bodyOfResponse,
                new HttpHeaders(), HttpStatus.CONFLICT, request);
    }
}
