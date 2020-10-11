package com.sid.message.proccessing.parsers;

import com.sid.message.proccessing.exceptions.UnSupportedMessageTypeException;
import com.sid.message.proccessing.exceptions.UnparsableMessageException;
import com.sid.message.proccessing.models.messages.GenericMessage;
import com.sid.message.proccessing.models.messages.SaleDetailsMessage;
import com.sid.message.proccessing.models.messages.SaleOperationMessage;
import com.sid.message.proccessing.models.messages.SalesMessage;
import com.sid.message.proccessing.utils.Constants;
import com.sid.message.proccessing.utils.Operation;

import java.util.Objects;

public class CsvParser implements Parser {

    public GenericMessage parse(String msgStr) throws UnparsableMessageException, UnSupportedMessageTypeException {
        GenericMessage message = null;
        if (Objects.isNull(msgStr) || msgStr.isEmpty()) {
            throw new UnparsableMessageException(Constants.EMPTY_MSG_ERROR);
        }
        String[] messageParts = msgStr.split(",");
        if (Objects.isNull(messageParts) || messageParts.length < 2) {
            throw new UnparsableMessageException(Constants.INVALID_MSG_ERROR);
        }
        Integer messageType = Integer.valueOf(messageParts[0]);
        String productType = messageParts[1];
        switch (messageType) {
            case 1: {
                Long price = Long.valueOf(messageParts[2]);
                message = new SaleDetailsMessage(productType, price);
                break;
            }
            case 2: {
                Integer numberOfSales = Integer.valueOf(messageParts[2]);
                Long price = Long.valueOf(messageParts[3]);
                message = new SalesMessage(productType, numberOfSales, price);
                break;
            }
            case 3: {
                Operation operation = Operation.lookupByOperation(messageParts[2]);
                Long adjutmentAmount = Long.valueOf(messageParts[3]);
                message = new SaleOperationMessage(productType, operation, adjutmentAmount);
                break;
            }
            default:
                throw new UnSupportedMessageTypeException(Constants.INVALID_MSG_TYPE_ERROR);

        }
        return message;
    }

}
