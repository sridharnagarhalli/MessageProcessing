package com.sid.message.proccessing.processors;

import com.sid.message.proccessing.exceptions.UnsupportedAdjustmentOperationException;
import com.sid.message.proccessing.models.Sale;
import com.sid.message.proccessing.models.messages.SaleOperationMessage;
import com.sid.message.proccessing.exceptions.InvalidAdjustmentException;
import com.sid.message.proccessing.exceptions.InvalidMessageException;
import com.sid.message.proccessing.services.SaleAdjustmentsReportService;
import com.sid.message.proccessing.services.SaleService;
import com.sid.message.proccessing.utils.Constants;
import org.apache.log4j.Logger;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static com.sid.message.proccessing.utils.Operation.ADD;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class SaleOperationProcessorTest {
    final static Logger LOGGER = Logger.getLogger(SaleOperationProcessorTest.class);

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
        SaleOperationProcessor processor = new SaleOperationProcessor(new SaleService(),
                        new SaleAdjustmentsReportService());
        try {
            Map<String, List<Sale>> map = processor.process(new SaleOperationMessage("milk", ADD, 5l));
            List<Sale> sales = map.get("milk");
            assertNotNull(sales);
            assertNotNull(SaleAdjustmentsReportService.getAdjustmentMap());
            assertEquals(1, SaleAdjustmentsReportService.getAdjustmentMap().size());

        } catch (InvalidMessageException e) {
            LOGGER.error(e.getLocalizedMessage());
        } catch (InvalidAdjustmentException e) {
            LOGGER.error(e.getLocalizedMessage());
        } catch (UnsupportedAdjustmentOperationException e) {
            LOGGER.error(e.getLocalizedMessage());
        }
    }

    @Test
    public void nullMsgShouldThrowInvalidMessageException() {
        SaleOperationProcessor processor = new SaleOperationProcessor(new SaleService(),
                        new SaleAdjustmentsReportService());
        Exception exception = assertThrows(InvalidMessageException.class, () -> {
            processor.process(null);
        });
        assertEquals(Constants.NULL_MSG_ERROR, exception.getLocalizedMessage());
    }

    @Test
    public void nullProductTypeShouldThrowInvalidMessageException() {
        SaleOperationProcessor processor = new SaleOperationProcessor(new SaleService(),
                        new SaleAdjustmentsReportService());
        Exception exception = assertThrows(InvalidMessageException.class, () -> {
            processor.process(new SaleOperationMessage(null, ADD, 20l));
        });
        assertEquals(Constants.NULL_PRODUCT_TYPE_ERROR,
                        exception.getLocalizedMessage());
    }

    @Test
    public void wrongOperatorShouldThrowUnsupportedAdjustmentOperationException() {
        SaleOperationProcessor processor = new SaleOperationProcessor(new SaleService(),
                        new SaleAdjustmentsReportService());
        Exception exception = assertThrows(UnsupportedAdjustmentOperationException.class, () -> {
            processor.process(new SaleOperationMessage("milk", null, 20l));
        });
        assertEquals(Constants.INVALID_OPERATION_ERROR,
                        exception.getLocalizedMessage());
    }
}
