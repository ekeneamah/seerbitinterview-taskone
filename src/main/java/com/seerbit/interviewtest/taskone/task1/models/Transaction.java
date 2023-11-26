package com.seerbit.interviewtest.taskone.task1.models;

import java.math.BigDecimal;
import java.time.Instant;

public class Transaction {
    private BigDecimal amount;
    private Instant timestamp;

    // Constructors, getters, and setters

    public Transaction() {
    }

    public Transaction(BigDecimal amount, Instant timestamp) {
        this.amount = amount;
        this.timestamp = timestamp;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public Instant getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Instant timestamp) {
        this.timestamp = timestamp;
    }
}