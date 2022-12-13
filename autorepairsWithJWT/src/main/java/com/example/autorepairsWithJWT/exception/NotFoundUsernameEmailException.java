package com.example.autorepairsWithJWT.exception;

//when registering new user
public class NotFoundUsernameEmailException extends IllegalArgumentException {
    public NotFoundUsernameEmailException() {
    }
    public NotFoundUsernameEmailException(String s) {
        super(s);
    }

    public NotFoundUsernameEmailException(String message, Throwable cause) {
        super(message, cause);
    }
}
