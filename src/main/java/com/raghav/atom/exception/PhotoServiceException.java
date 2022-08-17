package com.raghav.atom.exception;

public class PhotoServiceException extends Exception {
    public PhotoServiceException(){
        super("Internal Server Error");
    }
    public PhotoServiceException(String message) {
        super(message);
    }
}
