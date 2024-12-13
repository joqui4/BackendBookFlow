package com.BookFlow.TransactionService.interfaces.rest;

import com.BookFlow.TransactionService.domain.model.queries.GetAllTransactionStatesQuery;
import com.BookFlow.TransactionService.domain.model.queries.GetTransactionStateByIdQuery;
import com.BookFlow.TransactionService.domain.model.queries.GetTransactionStateByNameQuery;
import com.BookFlow.TransactionService.domain.services.TransactionStateQueryService;
import com.BookFlow.TransactionService.interfaces.rest.resources.TransactionStateResource;
import com.BookFlow.TransactionService.interfaces.rest.transform.TransactionStateResourceFromEntityAssembler;
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
@RequestMapping(value="/api/v1/transactionstates", produces = MediaType.APPLICATION_JSON_VALUE)
@Tag(name="Transaction States", description="Transaction States Management Endpoints")
public class TransactionStateController {
    private final TransactionStateQueryService transactionStateQueryService;

    public TransactionStateController(TransactionStateQueryService transactionStateQueryService){
        this.transactionStateQueryService = transactionStateQueryService;
    }

    @GetMapping("/transactionStateId/{transactionStateId}")
    public ResponseEntity<TransactionStateResource> getTransactionStateById(@PathVariable Long transactionStateId) {
        var getTransactionStateByIdQuery = new GetTransactionStateByIdQuery(transactionStateId);
        var transactionState = transactionStateQueryService.handle(getTransactionStateByIdQuery);
        if (transactionState.isEmpty()) return ResponseEntity.badRequest().build();
        var transactionStateResource = TransactionStateResourceFromEntityAssembler.toResourceFromEntity(transactionState.get());
        return ResponseEntity.ok(transactionStateResource);
    }
    @GetMapping
    public ResponseEntity<List<TransactionStateResource>> GetAllTransactionStates(){
        var getAllTransactionStatesQuery = new GetAllTransactionStatesQuery();
        var transactionStates = transactionStateQueryService.handle(getAllTransactionStatesQuery);
        var transactionStateResources = transactionStates.stream().map(TransactionStateResourceFromEntityAssembler::toResourceFromEntity).collect(Collectors.toList());
        return ResponseEntity.ok(transactionStateResources);
    }
    @GetMapping("/transactionStateName/{name}")
    public ResponseEntity<TransactionStateResource> getTransactionStateByName(@PathVariable String name) {
        var getTransactionStateByNameQuery = new GetTransactionStateByNameQuery(name);
        var transactionState = transactionStateQueryService.handle(getTransactionStateByNameQuery);
        if (transactionState.isEmpty()) return ResponseEntity.badRequest().build();
        var transactionStateResource = TransactionStateResourceFromEntityAssembler.toResourceFromEntity(transactionState.get());
        return ResponseEntity.ok(transactionStateResource);
    }
}
