package com.sid.message.proccessing.parsers;

import com.sid.message.proccessing.exceptions.UnSupportedMessageTypeException;
import com.sid.message.proccessing.exceptions.UnparsableMessageException;
import com.sid.message.proccessing.models.messages.GenericMessage;

public interface Parser {
    public GenericMessage parse(String msgStr) throws UnparsableMessageException, UnSupportedMessageTypeException;
}
