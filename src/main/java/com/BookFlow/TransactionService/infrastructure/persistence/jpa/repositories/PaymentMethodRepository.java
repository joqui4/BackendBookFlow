package com.BookFlow.TransactionService.infrastructure.persistence.jpa.repositories;

import com.BookFlow.TransactionService.domain.model.aggregates.PaymentMethod;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PaymentMethodRepository extends JpaRepository<PaymentMethod, Long> {
    Optional<PaymentMethod> findBypaymentMethodType(String paymentMethodType);
    boolean existsBypaymentMethodType(String paymentMethodType);

}
