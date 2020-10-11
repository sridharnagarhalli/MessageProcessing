package com.sid.message.proccessing.processors;

import com.sid.message.proccessing.exceptions.InvalidSaleException;
import com.sid.message.proccessing.models.Sale;
import com.sid.message.proccessing.models.messages.SalesMessage;
import com.sid.message.proccessing.services.SaleService;
import com.sid.message.proccessing.exceptions.InvalidMessageException;
import com.sid.message.proccessing.utils.Constants;

import java.util.List;
import java.util.Map;
import java.util.Objects;

public class SalesProcessor implements Processor<SalesMessage> {
    private SaleService saleService;

    public SalesProcessor(SaleService saleService) {
        this.saleService = saleService;
    }

    public Map<String, List<Sale>> process(SalesMessage message) throws InvalidSaleException, InvalidMessageException {
        if (Objects.isNull(message)) {
            throw new InvalidMessageException(Constants.NULL_MSG_ERROR);
        }
        if (Objects.isNull(message.getProductType())) {
            throw new InvalidMessageException(Constants.NULL_PRODUCT_TYPE_ERROR);
        }
        for (int i = 0; i < message.getNumberOfSales(); i++) {
            Sale sale = new Sale(message.getProductType(), message.getPrice());
            saleService.save(sale);
        }
        return SaleService.getSaleMap();
    }

}