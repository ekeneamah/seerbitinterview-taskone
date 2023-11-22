package com.seerbit.interviewtest.taskone.task1.models;

import java.math.BigDecimal;

public class Transaction {
    private BigDecimal amount;
    private String timestamp;

    // getters and setters

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }
}

