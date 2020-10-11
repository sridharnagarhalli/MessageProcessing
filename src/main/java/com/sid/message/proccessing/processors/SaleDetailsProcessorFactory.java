package com.sid.message.proccessing.processors;

import com.sid.message.proccessing.models.messages.SaleDetailsMessage;
import com.sid.message.proccessing.services.SaleService;

public class SaleDetailsProcessorFactory implements ProcessorFactory {

    public Processor<SaleDetailsMessage> getProcessor() {
        return new SaleDetailsProcessor(new SaleService());
    }

}