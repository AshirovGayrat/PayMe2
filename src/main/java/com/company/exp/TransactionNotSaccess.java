package com.company.exp;

public class TransactionNotSaccess extends RuntimeException{
    public TransactionNotSaccess(String message) {
        super(message);
    }
}
