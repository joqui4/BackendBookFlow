package com.BookFlow.TransactionService.domain.exceptions;

public class TransactionStateNotFoundException extends RuntimeException{
    public TransactionStateNotFoundException(Long id) {
        super("TransactionState with id " + id + " not found");
    }
}
