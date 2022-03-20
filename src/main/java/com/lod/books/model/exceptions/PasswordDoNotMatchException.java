package com.lod.books.model.exceptions;

public class PasswordDoNotMatchException extends RuntimeException {
    public PasswordDoNotMatchException() {
        super("Passwor do not match exception");
    }
}
