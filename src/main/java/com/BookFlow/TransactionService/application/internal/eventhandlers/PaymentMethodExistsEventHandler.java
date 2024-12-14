package com.BookFlow.TransactionService.application.internal.eventhandlers;

import com.BookFlow.TransactionService.domain.model.aggregates.PaymentMethod;
import com.BookFlow.TransactionService.domain.model.commands.PopulatePaymentMethod;
import com.BookFlow.TransactionService.infrastructure.persistence.jpa.repositories.PaymentMethodRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PaymentMethodExistsEventHandler {

    private static final List<PopulatePaymentMethod>INITIAL_PAYMENT_METHODS = List.of(
            new PopulatePaymentMethod(1L, "Credit Card"),
            new PopulatePaymentMethod(2L, "Debit Card"),
            new PopulatePaymentMethod(3L, "Net Banking"),
            new PopulatePaymentMethod(4L, "PayPal")
    );

    @Autowired
    private PaymentMethodRepository paymentMethodRepository;
    @EventListener(ApplicationReadyEvent.class)

    public void populatePaymentMethodTable(){
        for (PopulatePaymentMethod command : INITIAL_PAYMENT_METHODS){
            if (!paymentMethodRepository.existsBypaymentMethodType(command.getName())){
                PaymentMethod paymentMethod = new PaymentMethod(command.getId(), command.getName());
                paymentMethodRepository.save(paymentMethod);
            }
        }
    }
}
