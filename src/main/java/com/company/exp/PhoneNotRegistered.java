package com.company.exp;

public class PhoneNotRegistered extends RuntimeException{
    public PhoneNotRegistered(String message) {
        super(message);
    }
}
