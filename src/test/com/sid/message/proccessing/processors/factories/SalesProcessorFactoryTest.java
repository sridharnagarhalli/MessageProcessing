package com.sid.message.proccessing.processors.factories;

import com.sid.message.proccessing.processors.Processor;
import com.sid.message.proccessing.processors.SalesProcessor;
import com.sid.message.proccessing.processors.SalesProcessorFactory;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class SalesProcessorFactoryTest {

    @SuppressWarnings("rawtypes")
    @Test
    public void shouldGetSalesProcessor() {
        SalesProcessorFactory factory = new SalesProcessorFactory();
        Processor processor = factory.getProcessor();
        assertNotNull(processor);
        assertTrue(processor instanceof SalesProcessor);
    }
}
