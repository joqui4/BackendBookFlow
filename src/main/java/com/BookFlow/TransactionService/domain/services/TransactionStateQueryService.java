package com.BookFlow.TransactionService.domain.services;

import com.BookFlow.TransactionService.domain.model.aggregates.TransactionState;
import com.BookFlow.TransactionService.domain.model.queries.GetAllTransactionStatesQuery;
import com.BookFlow.TransactionService.domain.model.queries.GetTransactionStateByIdQuery;
import com.BookFlow.TransactionService.domain.model.queries.GetTransactionStateByNameQuery;

import java.util.List;
import java.util.Optional;

public interface TransactionStateQueryService {
    Optional<TransactionState> handle(GetTransactionStateByIdQuery query);
    Optional<TransactionState> handle(GetTransactionStateByNameQuery query);
    List<TransactionState> handle(GetAllTransactionStatesQuery query);
}
