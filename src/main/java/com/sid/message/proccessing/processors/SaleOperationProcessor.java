package com.sid.message.proccessing.processors;

import com.sid.message.proccessing.exceptions.UnsupportedAdjustmentOperationException;
import com.sid.message.proccessing.models.Sale;
import com.sid.message.proccessing.models.messages.SaleOperationMessage;
import com.sid.message.proccessing.models.reports.SaleAdjustment;
import com.sid.message.proccessing.services.SaleAdjustmentsReportService;
import com.sid.message.proccessing.services.SaleService;
import com.sid.message.proccessing.utils.Constants;
import com.sid.message.proccessing.exceptions.InvalidAdjustmentException;
import com.sid.message.proccessing.exceptions.InvalidMessageException;
import com.sid.message.proccessing.utils.Operation;

import java.util.List;
import java.util.Map;
import java.util.Objects;

public class SaleOperationProcessor implements Processor<SaleOperationMessage> {
    private SaleService saleService;
    private SaleAdjustmentsReportService adjustmentsReportService;

    public SaleOperationProcessor(SaleService saleService, SaleAdjustmentsReportService adjustmentsReportService) {
        super();
        this.saleService = saleService;
        this.adjustmentsReportService = adjustmentsReportService;
    }

    public Map<String, List<Sale>> process(SaleOperationMessage message) throws InvalidAdjustmentException,
                    InvalidMessageException, UnsupportedAdjustmentOperationException {
        if (Objects.isNull(message)) {
            throw new InvalidMessageException(Constants.NULL_MSG_ERROR);
        }
        if (Objects.isNull(message.getProductType())) {
            throw new InvalidMessageException(Constants.NULL_PRODUCT_TYPE_ERROR);
        }
        List<Sale> productSales = saleService.findByProductType(message.getProductType());
        for (Sale sale : productSales) {
            sale.setValue(Operation.perform(message.getOperation(), sale.getValue(), message.getAdjustedAmount()));
        }
        SaleAdjustment adjustment = new SaleAdjustment(message.getOperation(), message.getAdjustedAmount());
        adjustmentsReportService.saveAdjustment(adjustment, message.getProductType());
        return SaleService.getSaleMap();
    }

}
