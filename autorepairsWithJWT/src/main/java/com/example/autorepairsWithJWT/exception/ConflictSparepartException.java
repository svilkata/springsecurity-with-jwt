package com.example.autorepairsWithJWT.exception;

//when registering new user
public class ConflictSparepartException extends IllegalArgumentException {
    public ConflictSparepartException() {
    }
    public ConflictSparepartException(String s) {
        super(s);
    }

    public ConflictSparepartException(String message, Throwable cause) {
        super(message, cause);
    }
}
