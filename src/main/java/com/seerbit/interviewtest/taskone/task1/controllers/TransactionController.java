package com.seerbit.interviewtest.taskone.task1.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.seerbit.interviewtest.taskone.task1.models.Statistics;
import com.seerbit.interviewtest.taskone.task1.models.Transaction;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.Duration;
import java.time.Instant;
import java.util.LinkedList;
import java.util.List;

@RestController
public class TransactionController {

    // In-memory storage for transactions
    private static final List<Transaction> transactions = new LinkedList<>();

    /**
     * Endpoint to create a new transaction.
     * It must execute in constant time and memory (O(1)).
     *
     * @param transaction The transaction details (amount, timestamp).
     * @return ResponseEntity with HTTP status code indicating the result.
     */
    @PostMapping("/transactions")
    public ResponseEntity<Void> createTransaction(@RequestBody Transaction transaction) {
        Instant currentTimestamp = Instant.now();
        Instant transactionTimestamp = transaction.getTimestamp();

        // Check if the transaction timestamp is in the future
        if (transactionTimestamp.isAfter(currentTimestamp)) {
            return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).build();
        }

        // Check if the transaction is older than 30 seconds
        if (Duration.between(transactionTimestamp, currentTimestamp).getSeconds() > 30) {
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        }

        // Add the transaction to the in-memory storage
        transactions.add(transaction);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    /**
     * Endpoint to get statistics based on transactions in the last 30 seconds.
     * It must execute in constant time and memory (O(1)).
     *
     * @return ResponseEntity with the statistics or an error status code.
     */
    @GetMapping("/statistics")
    public ResponseEntity<Statistics> getStatistics() {
        Instant currentTimestamp = Instant.now();
        Instant last30Seconds = currentTimestamp.minusSeconds(30);

        // Filter transactions that occurred in the last 30 seconds
        List<Transaction> recentTransactions = new LinkedList<>();
        for (Transaction transaction : transactions) {
            if (!transaction.getTimestamp().isBefore(last30Seconds)) {
                recentTransactions.add(transaction);
            }
        }

        // Calculate statistics
        BigDecimal sum = BigDecimal.ZERO;
        BigDecimal max = BigDecimal.ZERO;
        BigDecimal min = BigDecimal.valueOf(Double.MAX_VALUE);
        long count = recentTransactions.size();

        for (Transaction transaction : recentTransactions) {
            BigDecimal amount = transaction.getAmount();
            sum = sum.add(amount);
            max = max.max(amount);
            min = min.min(amount);
        }

        // Calculate average using RoundingMode.HALF_UP
    BigDecimal avg = count > 0 ? sum.divide(BigDecimal.valueOf(count), 2, RoundingMode.HALF_UP) : BigDecimal.ZERO;

        // Create and return the statistics
        Statistics statistics = new Statistics(sum, avg, max, min, count);
        return ResponseEntity.ok(statistics);
    }

    /**
     * Endpoint to delete all transactions.
     *
     * @return ResponseEntity with HTTP status code indicating the result.
     */
    @DeleteMapping("/transactions")
    public ResponseEntity<Void> deleteTransactions() {
        // Clear the in-memory storage of transactions
        transactions.clear();
        return ResponseEntity.noContent().build();
    }
}
