package com.company.exp;

public class BalanceNotEnoughException extends RuntimeException{
    public BalanceNotEnoughException(String message) {
        super(message);
    }
}
