package com.sid.message.proccessing.processors;

import com.sid.message.proccessing.models.messages.GenericMessage;

public interface ProcessorFactory {
    Processor<? extends GenericMessage> getProcessor();
}
