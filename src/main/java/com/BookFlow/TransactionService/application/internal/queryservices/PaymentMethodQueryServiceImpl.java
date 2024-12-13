package com.BookFlow.TransactionService.application.internal.queryservices;

import com.BookFlow.TransactionService.domain.model.aggregates.PaymentMethod;
import com.BookFlow.TransactionService.domain.model.queries.GetAllPaymentMethodsQuery;
import com.BookFlow.TransactionService.domain.model.queries.GetPaymentMethodByIdQuery;
import com.BookFlow.TransactionService.domain.model.queries.GetPaymentMethodByNameQuery;
import com.BookFlow.TransactionService.domain.services.PaymentMethodQueryService;
import com.BookFlow.TransactionService.infrastructure.persistence.jpa.repositories.PaymentMethodRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PaymentMethodQueryServiceImpl implements PaymentMethodQueryService {
    private final PaymentMethodRepository paymentMethodRepository;

    public PaymentMethodQueryServiceImpl(PaymentMethodRepository paymentMethodRepository){
        this.paymentMethodRepository = paymentMethodRepository;
    }

    @Override
    public Optional<PaymentMethod> handle(GetPaymentMethodByNameQuery query) {
        return paymentMethodRepository.findBypaymentMethodType(query.name());
    }
    @Override
    public Optional<PaymentMethod> handle(GetPaymentMethodByIdQuery query) {
        return paymentMethodRepository.findById(query.id());
    }
    @Override
    public List<PaymentMethod> handle(GetAllPaymentMethodsQuery query) {
        return paymentMethodRepository.findAll();
    }

}
