package com.BookFlow.TransactionService.application.internal.eventhandlers;

import com.BookFlow.TransactionService.domain.model.aggregates.TransactionState;
import com.BookFlow.TransactionService.domain.model.commands.PopulateTransactionState;
import com.BookFlow.TransactionService.infrastructure.persistence.jpa.repositories.TransactionStateRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TransactionStateExistsEventHandler {
    private static final List<PopulateTransactionState> INITIAL_TRANSACTION_STATES = List.of();

    @Autowired
    private TransactionStateRepository transactionStateRepository;
    @EventListener(ApplicationReadyEvent.class)

    public void populateTransactionStateTable(){
        for (PopulateTransactionState command : INITIAL_TRANSACTION_STATES){
            if (!transactionStateRepository.existsBytransactionState(command.getName())){
                TransactionState transactionState = new TransactionState(command.getId(), command.getName());
                transactionStateRepository.save(transactionState);
            }
        }
    }
}
