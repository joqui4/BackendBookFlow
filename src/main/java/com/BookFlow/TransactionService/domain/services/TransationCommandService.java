package com.BookFlow.TransactionService.domain.services;

import com.BookFlow.TransactionService.domain.model.aggregates.Transaction;
import com.BookFlow.TransactionService.domain.model.commands.CreateTransactionCommand;

import java.util.Optional;

public interface TransationCommandService {
    Optional<Transaction> handle (CreateTransactionCommand command);
}
