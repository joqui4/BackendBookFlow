package com.BookFlow.TransactionService.interfaces.rest.resources;

public record TransactionResource(

        Long transactionId,
        String transactionDate,
        PaymentMethodResource paymentMethodId,
        TransactionStateResource transactionStateId
) {
}
