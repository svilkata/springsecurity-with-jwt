package com.example.autorepairsWithJWT.exception;

public class NotFoundItemToDeleteException extends IllegalArgumentException {
    public NotFoundItemToDeleteException() {
    }

    public NotFoundItemToDeleteException(String s) {
        super(s);
    }

    public NotFoundItemToDeleteException(String message, Throwable cause) {
        super(message, cause);
    }
}
