package com.sid.message.proccessing.models.reports;

import com.sid.message.proccessing.utils.Operation;

import java.io.Serializable;

public class SaleAdjustment implements Serializable {
    private Operation operation;
    private Long amount;

    public SaleAdjustment(final Operation operation, final Long amount) {
        super();
        this.operation = operation;
        this.amount = amount;
    }

    public String getOperation() {
        return operation.getOperation();
    }

    public void setOperation(Operation operation) {
        this.operation = operation;
    }

    public Long getAmount() {
        return amount;
    }

    public void setAmount(Long amount) {
        this.amount = amount;
    }

    @Override
    public String toString() {
        return "SaleAdjustment [" + getOperation() + " " + amount + "P]";
    }

}
