package com.isc.masrour.crudSample.sample.exception;

public class CustomNotFoundException extends RuntimeException{

    public CustomNotFoundException(String message){
        super(message);
    }
}
