package com.BookFlow.TransactionService.domain.model.aggregates;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;

@Entity
@Getter
public class TransactionState {
    @Id
    @NotNull
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long transactionId;

    @NotBlank
    @Column
    private String transactionState;

    public TransactionState(Long transactionId,String transactionState) {
        this.transactionId = transactionId;
        this.transactionState = transactionState;
    }

    public TransactionState() {

    }
    public Long getTransactionStateId(){
        return transactionId;
    }

}
