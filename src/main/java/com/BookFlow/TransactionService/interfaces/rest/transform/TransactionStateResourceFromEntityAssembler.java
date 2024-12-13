package com.BookFlow.TransactionService.interfaces.rest.transform;

import com.BookFlow.TransactionService.domain.model.aggregates.TransactionState;
import com.BookFlow.TransactionService.interfaces.rest.resources.TransactionStateResource;

public class TransactionStateResourceFromEntityAssembler {
    public static TransactionStateResource toResourceFromEntity(TransactionState transactionState) {
        return new TransactionStateResource(
                transactionState.getTransactionStateId(),
                transactionState.getTransactionState());
    }
}
