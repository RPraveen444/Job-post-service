package com.revature.jobpostservice.exceptions;

public class InvalidCredentialsException extends RuntimeException{
    String message;
    public InvalidCredentialsException(String message){
        this.message=message;
    }
}