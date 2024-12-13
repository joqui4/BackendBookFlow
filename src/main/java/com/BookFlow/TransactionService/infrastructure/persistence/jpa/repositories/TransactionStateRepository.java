package com.BookFlow.TransactionService.infrastructure.persistence.jpa.repositories;

import com.BookFlow.TransactionService.domain.model.aggregates.TransactionState;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface TransactionStateRepository extends JpaRepository<TransactionState, Long> {
    Optional<TransactionState> findBytransactionState(String transactionState);
    boolean existsBytransactionState(String transactionState);
}
