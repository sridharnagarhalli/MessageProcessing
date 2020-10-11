package com.sid.services;

import com.sid.message.proccessing.exceptions.InvalidAdjustmentException;
import com.sid.message.proccessing.models.reports.SaleAdjustment;
import com.sid.message.proccessing.services.SaleAdjustmentsReportService;
import com.sid.message.proccessing.utils.Constants;
import org.apache.log4j.Logger;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Map;

import static com.sid.message.proccessing.utils.Operation.ADD;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class SaleAdjustmentsReportServiceTest {
    final static Logger LOGGER = Logger.getLogger(SaleAdjustmentsReportServiceTest.class);

    @Test
    public void saveNonNullAdjustmentShouldWorkCorrectly() {
        SaleAdjustmentsReportService adjustmentsReportService = new SaleAdjustmentsReportService();
        SaleAdjustment adjustment = new SaleAdjustment(ADD, 20l);
        try {
            adjustmentsReportService.saveAdjustment(adjustment, "milk");
        } catch (InvalidAdjustmentException e) {
            LOGGER.error(e.getLocalizedMessage());
        }
        Map<String, List<SaleAdjustment>> report = SaleAdjustmentsReportService.getAdjustmentMap();
        assertNotNull(report);
        assertEquals(1, report.size());
    }

    @Test
    public void nullAdjustmentShouldThrowInvalidAdjustmentException() {
        SaleAdjustmentsReportService adjustmentsReportService = new SaleAdjustmentsReportService();
        Exception exception = assertThrows(InvalidAdjustmentException.class, () -> {
            adjustmentsReportService.saveAdjustment(null, "milk");
        });
        assertEquals(Constants.NULL_ADJUSTMENT_ERROR, exception.getLocalizedMessage());
    }

    @Test
    public void nullProductTypeShouldThrowInvalidAdjustmentException() {
        SaleAdjustmentsReportService adjustmentsReportService = new SaleAdjustmentsReportService();
        Exception exception = assertThrows(InvalidAdjustmentException.class, () -> {
            adjustmentsReportService.saveAdjustment(new SaleAdjustment(ADD, 20l), null);
        });
        assertEquals(Constants.NULL_PRODUCT_TYPE_ERROR,
                        exception.getLocalizedMessage());
    }
}