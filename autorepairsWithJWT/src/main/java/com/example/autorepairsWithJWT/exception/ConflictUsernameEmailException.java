package com.example.autorepairsWithJWT.exception;

//when registering new user
public class ConflictUsernameEmailException extends IllegalArgumentException {
    public ConflictUsernameEmailException() {
    }
    public ConflictUsernameEmailException(String s) {
        super(s);
    }

    public ConflictUsernameEmailException(String message, Throwable cause) {
        super(message, cause);
    }
}
