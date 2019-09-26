package com.charlesfrost.blb.exceptions;

public class ResourceNotFoundException extends RuntimeException {
    private String message;
    public ResourceNotFoundException(String s) {
        this.message=s;
    }
}
