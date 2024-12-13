package com.BookFlow.TransactionService.domain.model.commands;

public class PopulateTransactionState {
    private final Long transactionId;
    private final String transactionState;

    public PopulateTransactionState(Long transactionId, String transactionState) {
        this.transactionId = transactionId;
        this.transactionState = transactionState;
    }

    public Long getId() {
        return transactionId;
    }

    public String getName() {
        return transactionState;
    }
}
