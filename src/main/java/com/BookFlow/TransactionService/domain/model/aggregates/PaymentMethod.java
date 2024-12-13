package com.BookFlow.TransactionService.domain.model.aggregates;

import com.BookFlow.TransactionService.domain.model.commands.CreatePaymentMethodCommand;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;

@Entity
@Getter
public class PaymentMethod {
    @Id
    @NotNull
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long paymentMethodId;

    @NotNull
    @Column
    private String paymentMethodType;

    public PaymentMethod(Long paymentMethodId,String paymentMethodType) {
        this.paymentMethodId = paymentMethodId;
        this.paymentMethodType = paymentMethodType;
    }

    public PaymentMethod() {

    }
    public PaymentMethod(CreatePaymentMethodCommand command) {
        this.paymentMethodType = command.paymentMethodType();

    }
}
