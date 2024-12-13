package com.BookFlow.TransactionService.interfaces.rest.transform;

import com.BookFlow.TransactionService.domain.model.aggregates.Transaction;
import com.BookFlow.TransactionService.interfaces.rest.resources.TransactionResource;

public class TransactionResourceFromEntityAssembler {
    public static TransactionResource toResourceFromEntity(Transaction transaction) {
        return new TransactionResource(
                transaction.getTransactionId(),
                transaction.getTransactionDate(),
                PaymentMethodResourceFromEntityAssembler.toResourceFromEntity(transaction.getPaymentMethodId()),
                TransactionStateResourceFromEntityAssembler.toResourceFromEntity(transaction.getTransactionStateId())
                );
    }
}
