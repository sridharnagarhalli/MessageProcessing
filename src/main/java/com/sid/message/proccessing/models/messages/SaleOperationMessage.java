package com.sid.message.proccessing.models.messages;

import com.sid.message.proccessing.utils.Operation;

public class SaleOperationMessage extends GenericMessage {

    private Operation operation;
    private Long adjustedAmount;

    public SaleOperationMessage(final String productType, final Operation operation, final Long adjustmentAmount) {
        super(3, productType);
        this.operation = operation;
        this.adjustedAmount = adjustmentAmount;
    }

    public Operation getOperation() {
        return operation;
    }

    public void setOperation(Operation operation) {
        this.operation = operation;
    }

    public Long getAdjustedAmount() {
        return adjustedAmount;
    }

    public void setAdjustedAmount(Long adjustedAmount) {
        this.adjustedAmount = adjustedAmount;
    }

}
