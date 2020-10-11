package com.sid.message.proccessing.processors;

import com.sid.message.proccessing.exceptions.InvalidSaleException;
import com.sid.message.proccessing.models.Sale;
import com.sid.message.proccessing.models.messages.SaleDetailsMessage;
import com.sid.message.proccessing.services.SaleService;
import com.sid.message.proccessing.utils.Constants;
import com.sid.message.proccessing.exceptions.InvalidMessageException;
import org.apache.log4j.Logger;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class SaleDetailsProcessorTest {
    final static Logger LOGGER = Logger.getLogger(SaleDetailsProcessorTest.class);

    @BeforeEach
    public void initSeeMap() {
        List<Sale> sales = new ArrayList<Sale>();
        sales.add(new Sale("milk", 20l));
        sales.add(new Sale("milk", 15l));
        sales.add(new Sale("milk", 20l));
        SaleService.getSaleMap().put("milk", sales);
    }

    @Test
    public void nonNullSalesMsgShouldBeProccesedCorrectly() {
        SaleDetailsProcessor processor = new SaleDetailsProcessor(new SaleService());
        try {
            Map<String, List<Sale>> map = processor.process(new SaleDetailsMessage("milk", 20l));
            List<Sale> sales = map.get("milk");
            assertNotNull(sales);
            assertEquals(4, sales.size());
        } catch (InvalidSaleException | InvalidMessageException e) {
            LOGGER.error(e.getLocalizedMessage());
        }
    }

    @Test
    public void nullMsgShouldThrowInvalidMessageException() {
        SaleDetailsProcessor processor = new SaleDetailsProcessor(new SaleService());
        Exception exception = assertThrows(InvalidMessageException.class, () -> {
            processor.process(null);
        });
        Assertions.assertEquals(Constants.NULL_MSG_ERROR, exception.getLocalizedMessage());
    }

    @Test
    public void nullProductTypeShouldThrowInvalidMessageException() {
        SaleDetailsProcessor processor = new SaleDetailsProcessor(new SaleService());
        Exception exception = assertThrows(InvalidMessageException.class, () -> {
            processor.process(new SaleDetailsMessage(null, 20l));
        });
        assertEquals(Constants.NULL_PRODUCT_TYPE_ERROR,
                        exception.getLocalizedMessage());
    }

}