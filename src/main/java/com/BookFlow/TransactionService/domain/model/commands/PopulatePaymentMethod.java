package com.BookFlow.TransactionService.domain.model.commands;

public class PopulatePaymentMethod {
    private final Long paymentMethodId;
    private final String paymentMethodType;

    public PopulatePaymentMethod(Long paymentMethodId, String paymentMethodType) {
        this.paymentMethodId = paymentMethodId;
        this.paymentMethodType = paymentMethodType;
    }
    public Long getId() {
        return paymentMethodId;
    }
    public String getName() {
        return paymentMethodType;
    }
}
