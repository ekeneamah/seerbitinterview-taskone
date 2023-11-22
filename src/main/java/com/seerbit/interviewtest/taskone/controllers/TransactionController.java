package com.seerbit.interviewtest.taskone.controllers;

import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;

@RestController
@RequestMapping("/api/transactions")
public class TransactionController {

    @PostMapping
    public String createTransaction(@RequestBody Transaction transaction) {
        // Use the 'transaction' object to access 'amount' and 'timestamp'
        // Perform necessary operations, e.g., store the transaction in a database

        return "Transaction created with amount: " + transaction.getAmount()
                + ", timestamp: " + transaction.getTimestamp();
    }

    // Other methods for GET, PUT, DELETE as needed
}

