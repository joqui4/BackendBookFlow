package com.BookFlow.TransactionService.interfaces.rest.resources;

public record CreateTransactionResource(
        String transactionDate,
        Long paymentMethodId,
        Long transactionStateId
) {
}
