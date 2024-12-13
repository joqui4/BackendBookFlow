package com.BookFlow.TransactionService.domain.model.commands;


public record CreateTransactionCommand(String transactionDate, Long paymentMethodId, Long transactionStateId) {
}
