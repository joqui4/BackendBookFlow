package com.BookFlow.TransactionService.domain.services;

import com.BookFlow.TransactionService.domain.model.aggregates.Transaction;
import com.BookFlow.TransactionService.domain.model.queries.GetAllTransactionsQuery;
import com.BookFlow.TransactionService.domain.model.queries.GetTransactionByIdQuery;

import java.util.List;
import java.util.Optional;

public interface TransactionQueryService {
    Optional<Transaction> handle(GetTransactionByIdQuery query);
    List<Transaction> handle(GetAllTransactionsQuery query);
}
