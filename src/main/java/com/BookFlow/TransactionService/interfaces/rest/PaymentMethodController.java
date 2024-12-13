package com.BookFlow.TransactionService.interfaces.rest;

import com.BookFlow.TransactionService.domain.model.queries.GetAllPaymentMethodsQuery;
import com.BookFlow.TransactionService.domain.model.queries.GetPaymentMethodByIdQuery;
import com.BookFlow.TransactionService.domain.model.queries.GetPaymentMethodByNameQuery;
import com.BookFlow.TransactionService.domain.services.PaymentMethodQueryService;
import com.BookFlow.TransactionService.interfaces.rest.resources.PaymentMethodResource;
import com.BookFlow.TransactionService.interfaces.rest.transform.PaymentMethodResourceFromEntityAssembler;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value="/api/v1/paymentmethods", produces = MediaType.APPLICATION_JSON_VALUE)
@Tag(name="Payment Methods", description="Payment Methods Management Endpoints")
public class PaymentMethodController {
    private final PaymentMethodQueryService paymentMethodQueryService;
    public PaymentMethodController(PaymentMethodQueryService paymentMethodQueryService){
        this.paymentMethodQueryService = paymentMethodQueryService;
    }
    @GetMapping("/bookId/{paymentmethodId}")
    public ResponseEntity<PaymentMethodResource> getPaymentMethodIdById(@PathVariable Long paymentmethodId) {
        var getPaymentMethodByIdQuery = new GetPaymentMethodByIdQuery(paymentmethodId);
        var payment_method = paymentMethodQueryService.handle(getPaymentMethodByIdQuery);
        if (payment_method.isEmpty()) return ResponseEntity.badRequest().build();
        var paymentMethodResource = PaymentMethodResourceFromEntityAssembler.toResourceFromEntity(payment_method.get());
        return ResponseEntity.ok(paymentMethodResource);
    }
    @GetMapping
    public ResponseEntity<List<PaymentMethodResource>> GetAllPaymentMethods(){
        var getAllPaymentMethodsQuery = new GetAllPaymentMethodsQuery();
        var paymentMethods = paymentMethodQueryService.handle(getAllPaymentMethodsQuery);
        var paymentMethodResources = paymentMethods.stream().map(PaymentMethodResourceFromEntityAssembler::toResourceFromEntity).collect(Collectors.toList());
        return ResponseEntity.ok(paymentMethodResources);
    }
    @GetMapping("/payment_name/{name}")
    public ResponseEntity<PaymentMethodResource> getPaymentMethodsByName(@PathVariable String name) {
        var getPaymentMethodByNameQuery = new GetPaymentMethodByNameQuery(name);
        var paymentMethod = paymentMethodQueryService.handle(getPaymentMethodByNameQuery);
        if (paymentMethod.isEmpty()) return ResponseEntity.badRequest().build();
        var paymentMethodResource = PaymentMethodResourceFromEntityAssembler.toResourceFromEntity(paymentMethod.get());
        return ResponseEntity.ok(paymentMethodResource);
    }
}
