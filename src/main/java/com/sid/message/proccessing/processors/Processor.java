package com.sid.message.proccessing.processors;

import java.util.List;
import java.util.Map;

import com.sid.message.proccessing.exceptions.InvalidAdjustmentException;
import com.sid.message.proccessing.exceptions.InvalidMessageException;
import com.sid.message.proccessing.exceptions.InvalidSaleException;
import com.sid.message.proccessing.exceptions.UnsupportedAdjustmentOperationException;
import com.sid.message.proccessing.models.Sale;
import com.sid.message.proccessing.models.messages.GenericMessage;

public interface Processor<T extends GenericMessage> {

    public Map<String, List<Sale>> process(T message) throws InvalidSaleException, InvalidAdjustmentException, InvalidMessageException, UnsupportedAdjustmentOperationException;
}
