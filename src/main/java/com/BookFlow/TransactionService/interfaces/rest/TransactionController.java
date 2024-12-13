package com.BookFlow.TransactionService.interfaces.rest;

import com.BookFlow.TransactionService.domain.model.queries.GetAllTransactionsQuery;
import com.BookFlow.TransactionService.domain.model.queries.GetTransactionByIdQuery;
import com.BookFlow.TransactionService.domain.services.TransactionQueryService;
import com.BookFlow.TransactionService.domain.services.TransationCommandService;
import com.BookFlow.TransactionService.interfaces.rest.resources.CreateTransactionResource;
import com.BookFlow.TransactionService.interfaces.rest.resources.TransactionResource;
import com.BookFlow.TransactionService.interfaces.rest.transform.CreateTransactionCommandFromResourceAssembler;
import com.BookFlow.TransactionService.interfaces.rest.transform.TransactionResourceFromEntityAssembler;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value="/api/v1/transactions", produces = MediaType.APPLICATION_JSON_VALUE)
@Tag(name="Transactions", description="Transactions Management Endpoints")
public class TransactionController {
    private final TransactionQueryService transactionQueryService;
    private final TransationCommandService transactionCommandService;

    public TransactionController(TransactionQueryService transactionQueryService, TransationCommandService transactionCommandService){
        this.transactionQueryService = transactionQueryService;
        this.transactionCommandService = transactionCommandService;
    }
    @PostMapping
    public ResponseEntity<TransactionResource> CreateTransaction(@RequestBody CreateTransactionResource resource) {
        var createTransactionCommand = CreateTransactionCommandFromResourceAssembler.toCommandFromResource(resource); // CreateCategoryCommand
        var transaction = transactionCommandService.handle(createTransactionCommand); //
        if (transaction.isEmpty()) return ResponseEntity.badRequest().build();
        var transactionResource = TransactionResourceFromEntityAssembler.toResourceFromEntity(transaction.get());
        return new ResponseEntity<>(transactionResource, HttpStatus.CREATED);
    }
    @GetMapping("/bookId/{transactionId}")
    public ResponseEntity<TransactionResource> getTransactionById(@PathVariable Long transactionId) {
        var getTransactionByIdQuery = new GetTransactionByIdQuery(transactionId);
        var transaction = transactionQueryService.handle(getTransactionByIdQuery);
        if (transaction.isEmpty()) return ResponseEntity.badRequest().build();
        var transactionResource = TransactionResourceFromEntityAssembler.toResourceFromEntity(transaction.get());
        return ResponseEntity.ok(transactionResource);
    }
    @GetMapping
    public ResponseEntity<List<TransactionResource>> GetAllTransactions(){
        var getAllTransactionsQuery = new GetAllTransactionsQuery();
        var transactions = transactionQueryService.handle(getAllTransactionsQuery);
        var transactionResources = transactions.stream().map(TransactionResourceFromEntityAssembler::toResourceFromEntity).collect(Collectors.toList());
        return ResponseEntity.ok(transactionResources);
    }
}
