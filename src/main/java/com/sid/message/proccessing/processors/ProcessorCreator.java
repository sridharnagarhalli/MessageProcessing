package com.sid.message.proccessing.processors;

import com.sid.message.proccessing.exceptions.InvalidMessageTypeException;
import com.sid.message.proccessing.exceptions.UnSupportedMessageTypeException;
import com.sid.message.proccessing.utils.Constants;

import java.util.Objects;

public class ProcessorCreator {

    public static ProcessorFactory getFacory(Integer messageType)
                    throws UnSupportedMessageTypeException, InvalidMessageTypeException {
        if (Objects.isNull(messageType)) {
            throw new InvalidMessageTypeException(Constants.NULL_MSG_TYPE_ERROR);
        }
        switch (messageType) {
            case 1:
                return new SaleDetailsProcessorFactory();
            case 2:
                return new SalesProcessorFactory();
            case 3:
                return new SaleOperationsProcessorFactory();
            default:
                throw new UnSupportedMessageTypeException(Constants.INVALID_MSG_TYPE_ERROR);
        }
    }
}