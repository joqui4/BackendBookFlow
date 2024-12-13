package com.BookFlow.TransactionService.interfaces.rest.transform;

import com.BookFlow.TransactionService.domain.model.commands.CreateTransactionCommand;
import com.BookFlow.TransactionService.interfaces.rest.resources.CreateTransactionResource;

public class CreateTransactionCommandFromResourceAssembler {
    public static CreateTransactionCommand toCommandFromResource(CreateTransactionResource resource) {
        return new CreateTransactionCommand(
                resource.transactionDate(),
                resource.paymentMethodId(),
                resource.transactionStateId()
        );
    }
}
