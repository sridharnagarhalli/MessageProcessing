package com.sid.message.proccessing.processors;

import com.sid.message.proccessing.models.messages.SaleOperationMessage;
import com.sid.message.proccessing.services.SaleAdjustmentsReportService;
import com.sid.message.proccessing.services.SaleService;

public class SaleOperationsProcessorFactory implements ProcessorFactory {
    public Processor<SaleOperationMessage> getProcessor() {
        return new SaleOperationProcessor(new SaleService(),new SaleAdjustmentsReportService());
    }
}
