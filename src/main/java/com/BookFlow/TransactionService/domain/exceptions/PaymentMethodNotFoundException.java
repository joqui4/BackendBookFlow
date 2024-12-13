package com.BookFlow.TransactionService.domain.exceptions;

public class PaymentMethodNotFoundException extends RuntimeException{
    public PaymentMethodNotFoundException(Long id) {
        super(STR."PaymentMethod with id \{id} not found");
    }
}
