package com.alltodo.todo.exception;

public class InvalidLoginMethodException extends RuntimeException {
    public InvalidLoginMethodException(String s) {
        super(s);
    }
}
