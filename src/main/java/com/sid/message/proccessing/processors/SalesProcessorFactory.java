package com.sid.message.proccessing.processors;

import com.sid.message.proccessing.models.messages.SalesMessage;
import com.sid.message.proccessing.services.SaleService;

public class SalesProcessorFactory implements ProcessorFactory {

    public Processor<SalesMessage> getProcessor() {
        return new SalesProcessor(new SaleService());
    }

}
