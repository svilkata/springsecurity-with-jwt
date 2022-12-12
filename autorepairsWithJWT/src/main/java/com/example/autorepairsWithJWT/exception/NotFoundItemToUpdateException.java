package com.example.autorepairsWithJWT.exception;

public class NotFoundItemToUpdateException extends IllegalArgumentException{
    public NotFoundItemToUpdateException() {
    }

    public NotFoundItemToUpdateException(String s) {
        super(s);
    }

    public NotFoundItemToUpdateException(String message, Throwable cause) {
        super(message, cause);
    }
}
