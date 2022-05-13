package com.company.exp;

public class PasswordOrPhoneWrongException extends RuntimeException{
    public PasswordOrPhoneWrongException(String message) {
        super(message);
    }
}
