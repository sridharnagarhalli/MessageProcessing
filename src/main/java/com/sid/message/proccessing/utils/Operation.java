package com.sid.message.proccessing.utils;

import com.sid.message.proccessing.exceptions.UnsupportedAdjustmentOperationException;

import java.util.Objects;
import java.util.stream.Stream;

public enum Operation {

    ADD("+", "Add"),
    SUBSTRACT("-", "Minus"),
    MULTIPLY("*", "Multiply");

    final String operator;
    final String operation;

    Operation(final String operator, final String operation) {
        this.operator = operator;
        this.operation = operation;
    }

    public String getOperator() {
        return operator;
    }

    public String getOperation() {
        return operation;
    }

    public static Operation lookupByOperation(final String operation) {
        return Stream.of(Operation.values()).filter(x -> x.operation.equalsIgnoreCase(operation)).findFirst().get();
    }

    public static Long perform(final Operation operation, final Long value, final Long amount)
                    throws UnsupportedAdjustmentOperationException {
        if(Objects.isNull(operation)) {
            throw new UnsupportedAdjustmentOperationException(Constants.INVALID_OPERATION_ERROR);
        }
        switch (operation) {
            case ADD:
                return value + amount;
            case MULTIPLY:
                return value * amount;
            case SUBSTRACT:
                return value - amount;
            default:
                throw new UnsupportedAdjustmentOperationException(Constants.INVALID_OPERATION_ERROR);

        }
    }

}
