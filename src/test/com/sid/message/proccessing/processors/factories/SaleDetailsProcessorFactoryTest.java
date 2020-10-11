package com.sid.message.proccessing.processors.factories;

import com.sid.message.proccessing.processors.Processor;
import com.sid.message.proccessing.processors.SaleDetailsProcessor;
import com.sid.message.proccessing.processors.SaleDetailsProcessorFactory;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class SaleDetailsProcessorFactoryTest {

    @SuppressWarnings("rawtypes")
    @Test
    public void shouldGetSaleDetailsProcessor() {
        SaleDetailsProcessorFactory factory = new SaleDetailsProcessorFactory();
        Processor processor = factory.getProcessor();
        assertNotNull(processor);
        assertTrue(processor instanceof SaleDetailsProcessor);
    }

}
