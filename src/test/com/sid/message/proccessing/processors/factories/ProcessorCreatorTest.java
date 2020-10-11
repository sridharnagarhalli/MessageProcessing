package com.sid.message.proccessing.processors.factories;

import com.sid.message.proccessing.exceptions.UnSupportedMessageTypeException;
import com.sid.message.proccessing.exceptions.InvalidMessageTypeException;
import com.sid.message.proccessing.processors.ProcessorCreator;
import com.sid.message.proccessing.processors.ProcessorFactory;
import com.sid.message.proccessing.processors.SaleDetailsProcessorFactory;
import com.sid.message.proccessing.processors.SaleOperationsProcessorFactory;
import com.sid.message.proccessing.processors.SalesProcessorFactory;
import com.sid.message.proccessing.utils.Constants;
import org.apache.log4j.Logger;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class ProcessorCreatorTest {
    final static Logger LOGGER = Logger.getLogger(ProcessorCreatorTest.class);

    @Test
    public void nullInputShouldThrowInvalidMessageTypeException() {
        Exception exception = assertThrows(InvalidMessageTypeException.class, () -> {
            ProcessorCreator.getFacory(null);
        });
        assertEquals(Constants.NULL_MSG_TYPE_ERROR, exception.getLocalizedMessage());
    }

    @Test
    public void unsupportedMessageTypesShouldThrowUnSupportedMessageTypeException() {
        Exception exception = assertThrows(UnSupportedMessageTypeException.class, () -> {
            ProcessorCreator.getFacory(4);
        });
        assertEquals(Constants.INVALID_MSG_TYPE_ERROR, exception.getLocalizedMessage());
    }

    @Test
    public void shouldGetSaleDetailsProcessorFactoryForMessageType1() {
        try {
            ProcessorFactory processorFactory = ProcessorCreator.getFacory(1);
            assertNotNull(processorFactory);
            assertTrue(processorFactory instanceof SaleDetailsProcessorFactory);
        } catch (UnSupportedMessageTypeException | InvalidMessageTypeException e) {
            LOGGER.error(e.getLocalizedMessage());
        }
    }

    @Test
    public void shouldGetSalesProcessorFactoryForMessageType2() {
        try {
            ProcessorFactory processorFactory = ProcessorCreator.getFacory(2);
            assertNotNull(processorFactory);
            assertTrue(processorFactory instanceof SalesProcessorFactory);
        } catch (UnSupportedMessageTypeException | InvalidMessageTypeException e) {
            LOGGER.error(e.getLocalizedMessage());
        }
    }

    @Test
    public void shouldGetSaleOperationsProcessorFactoryForMessageType3() {
        try {
            ProcessorFactory processorFactory = ProcessorCreator.getFacory(3);
            assertNotNull(processorFactory);
            assertTrue(processorFactory instanceof SaleOperationsProcessorFactory);
        } catch (UnSupportedMessageTypeException | InvalidMessageTypeException e) {
            LOGGER.error(e.getLocalizedMessage());
        }
    }
}

