package com.sid.message.proccessing.processors;

import com.sid.message.proccessing.models.Sale;
import com.sid.message.proccessing.models.messages.SalesMessage;
import com.sid.message.proccessing.exceptions.InvalidMessageException;
import com.sid.message.proccessing.exceptions.InvalidSaleException;
import com.sid.message.proccessing.services.SaleService;
import com.sid.message.proccessing.utils.Constants;
import org.apache.log4j.Logger;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class SalesProcessorTest {
    final static Logger LOGGER = Logger.getLogger(SalesProcessorTest.class);

    @BeforeEach
    public void initSeeMap() {
        SaleService.getSaleMap().clear();
    }

    @Test
    public void nonNullSalesMsgShouldBeProccesedCorrectly() {
        SalesProcessor processor = new SalesProcessor(new SaleService());
        try {
            Map<String, List<Sale>> map = processor.process(new SalesMessage("milk", 20, 20l));
            List<Sale> sales = map.get("milk");
            assertNotNull(sales);
            assertEquals(20, sales.size());
            assertFalse(map.isEmpty());

        } catch (InvalidSaleException | InvalidMessageException e) {
            LOGGER.error(e.getLocalizedMessage());
        }
    }

    @Test
    public void nullSalesMsgShouldThrowInvalidMessageException() {
        SalesProcessor processor = new SalesProcessor(new SaleService());
        Exception exception = assertThrows(InvalidMessageException.class, () -> {
            processor.process(null);
        });
        assertEquals(Constants.NULL_MSG_ERROR, exception.getLocalizedMessage());
    }

    @Test
    public void nullProductTypeShouldThrowInvalidMessageException() {
        SalesProcessor processor = new SalesProcessor(new SaleService());
        Exception exception = assertThrows(InvalidMessageException.class, () -> {
            processor.process(new SalesMessage(null, 20, 20l));
        });
        assertEquals(Constants.NULL_PRODUCT_TYPE_ERROR,
                        exception.getLocalizedMessage());
    }
}
