package com.BookFlow.TransactionService.application.internal.commandservices;

import com.BookFlow.TransactionService.domain.model.aggregates.Transaction;
import com.BookFlow.TransactionService.domain.model.commands.CreateTransactionCommand;
import com.BookFlow.TransactionService.domain.services.TransationCommandService;
import com.BookFlow.TransactionService.infrastructure.persistence.jpa.repositories.TransactionRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class TransactionCommandServiceImpl implements TransationCommandService {
    private final TransactionRepository transactionRepository;

    public TransactionCommandServiceImpl(TransactionRepository transactionRepository){
        this.transactionRepository = transactionRepository;
    }

    @Override
    public Optional<Transaction> handle(CreateTransactionCommand command){
        var transaction = new Transaction(command);
        transactionRepository.save(transaction);
        return Optional.of(transaction);
    }
}
