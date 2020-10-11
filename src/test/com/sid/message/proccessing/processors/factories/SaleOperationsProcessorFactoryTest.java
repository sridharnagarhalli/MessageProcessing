package com.sid.message.proccessing.processors.factories;

import com.sid.message.proccessing.processors.Processor;
import com.sid.message.proccessing.processors.SaleOperationProcessor;
import com.sid.message.proccessing.processors.SaleOperationsProcessorFactory;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class SaleOperationsProcessorFactoryTest {

    @SuppressWarnings("rawtypes")
    @Test
    public void shouldGetSaleOperationProcessor() {
        SaleOperationsProcessorFactory factory = new SaleOperationsProcessorFactory();
        Processor processor = factory.getProcessor();
        assertNotNull(processor);
        assertTrue(processor instanceof SaleOperationProcessor);
    }
}
