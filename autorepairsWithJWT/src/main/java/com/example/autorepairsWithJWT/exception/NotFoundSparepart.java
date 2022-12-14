package com.example.autorepairsWithJWT.exception;

public class NotFoundSparepart extends IllegalArgumentException{
    public NotFoundSparepart() {
    }

    public NotFoundSparepart(String s) {
        super(s);
    }

    public NotFoundSparepart(String message, Throwable cause) {
        super(message, cause);
    }
}
