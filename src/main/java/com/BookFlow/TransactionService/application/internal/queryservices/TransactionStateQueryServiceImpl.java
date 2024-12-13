package com.BookFlow.TransactionService.application.internal.queryservices;

import com.BookFlow.TransactionService.domain.model.aggregates.TransactionState;
import com.BookFlow.TransactionService.domain.model.queries.GetAllTransactionStatesQuery;
import com.BookFlow.TransactionService.domain.model.queries.GetTransactionStateByIdQuery;
import com.BookFlow.TransactionService.domain.model.queries.GetTransactionStateByNameQuery;
import com.BookFlow.TransactionService.domain.services.TransactionStateQueryService;
import com.BookFlow.TransactionService.infrastructure.persistence.jpa.repositories.TransactionStateRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TransactionStateQueryServiceImpl implements TransactionStateQueryService {

    private final TransactionStateRepository transactionStateRepository;

    public TransactionStateQueryServiceImpl(TransactionStateRepository transactionStateRepository){
        this.transactionStateRepository = transactionStateRepository;
    }

    @Override
    public Optional<TransactionState> handle(GetTransactionStateByNameQuery query) {
        return transactionStateRepository.findBytransactionState(query.name());
    }
    @Override
    public Optional<TransactionState> handle(GetTransactionStateByIdQuery query) {
        return transactionStateRepository.findById(query.id());
    }
    @Override
    public List<TransactionState> handle(GetAllTransactionStatesQuery query) {
        return transactionStateRepository.findAll();
    }

}
