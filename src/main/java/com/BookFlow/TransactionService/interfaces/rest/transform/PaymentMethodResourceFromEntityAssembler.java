package com.BookFlow.TransactionService.interfaces.rest.transform;

import com.BookFlow.TransactionService.domain.model.aggregates.PaymentMethod;
import com.BookFlow.TransactionService.interfaces.rest.resources.PaymentMethodResource;

public class PaymentMethodResourceFromEntityAssembler {
    public static PaymentMethodResource toResourceFromEntity(PaymentMethod paymentMethod) {
        return new PaymentMethodResource(
                paymentMethod.getPaymentMethodId(),
                paymentMethod.getPaymentMethodType()
        );
    }
}
