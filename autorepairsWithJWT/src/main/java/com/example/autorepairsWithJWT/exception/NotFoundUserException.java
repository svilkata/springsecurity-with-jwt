package com.example.autorepairsWithJWT.exception;

//when user is not present in the database
public class NotFoundUserException extends IllegalArgumentException {
    public NotFoundUserException() {
    }
    public NotFoundUserException(String s) {
        super(s);
    }

    public NotFoundUserException(String message, Throwable cause) {
        super(message, cause);
    }
}
