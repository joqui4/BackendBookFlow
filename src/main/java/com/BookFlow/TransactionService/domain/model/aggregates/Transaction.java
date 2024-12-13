package com.BookFlow.TransactionService.domain.model.aggregates;

import com.BookFlow.TransactionService.domain.model.commands.CreateTransactionCommand;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Getter
public class Transaction {
    @Id
    @NotNull
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long transactionId;

    @NotBlank
    @DateTimeFormat
    private String transactionDate;

    @ManyToOne
    @JoinColumn(name = "payment_method_id")
    private PaymentMethod paymentMethodId;

    @ManyToOne
    @JoinColumn(name = "transaction_state_id")
    private TransactionState transactionStateId;
    public Transaction(String transactionDate, Long paymentMethodId, Long transactionStateId) {
        this.transactionDate = transactionDate;
        this.paymentMethodId = new PaymentMethod(paymentMethodId, "");
        this.transactionStateId = new TransactionState(transactionStateId, "");
    }
    public Transaction() {
    }
    public Transaction(CreateTransactionCommand command) {
        this.transactionDate = command.transactionDate();
        this.paymentMethodId = new PaymentMethod(command.paymentMethodId(), "");
        this.transactionStateId = new TransactionState(command.transactionStateId(), "");
    }

}
