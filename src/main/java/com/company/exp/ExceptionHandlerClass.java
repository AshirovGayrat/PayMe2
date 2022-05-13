package com.company.exp;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ExceptionHandlerClass {

    @ExceptionHandler({BadRequestException.class, ProfileAlreadyExists.class,
            ItemNotFoundException.class,PasswordOrPhoneWrongException.class, BalanceNotEnoughException.class})
    public ResponseEntity<?> badRequestHandler(RuntimeException e){
        return ResponseEntity.badRequest().body(e.getMessage());
    }

    @ExceptionHandler({TransactionNotSaccess.class})
    public ResponseEntity<?> error(RuntimeException e){
        return ResponseEntity.badRequest().body(e.getMessage());
    }

    @ExceptionHandler({PhoneNotRegistered.class})
    public ResponseEntity<?> appForbidden(RuntimeException e){
        return ResponseEntity.status(HttpStatus.FORBIDDEN).body(e.getMessage());
    }

}
