package com.BookFlow.TransactionService.application.internal.queryservices;
import com.BookFlow.TransactionService.domain.model.aggregates.Transaction;
import com.BookFlow.TransactionService.domain.model.queries.GetAllTransactionsQuery;
import com.BookFlow.TransactionService.domain.model.queries.GetTransactionByIdQuery;
import com.BookFlow.TransactionService.domain.services.TransactionQueryService;
import com.BookFlow.TransactionService.infrastructure.persistence.jpa.repositories.TransactionRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TransactionQueryServiceImpl implements TransactionQueryService {
    private final TransactionRepository transactionRepository;

    public TransactionQueryServiceImpl(TransactionRepository transactionRepository){
        this.transactionRepository = transactionRepository;
    }


    @Override
    public Optional<Transaction> handle(GetTransactionByIdQuery query) {
        return transactionRepository.findById(query.transactionId());
    }
    @Override
    public List<Transaction> handle(GetAllTransactionsQuery query) {
        return transactionRepository.findAll();
    }
}
