package com.sid.message.proccessing.processors;

import com.sid.message.proccessing.exceptions.InvalidSaleException;
import com.sid.message.proccessing.models.Sale;
import com.sid.message.proccessing.models.messages.SaleDetailsMessage;
import com.sid.message.proccessing.services.SaleService;
import com.sid.message.proccessing.utils.Constants;
import com.sid.message.proccessing.exceptions.InvalidMessageException;

import java.util.List;
import java.util.Map;
import java.util.Objects;

public class SaleDetailsProcessor implements Processor<SaleDetailsMessage> {
    private SaleService saleService;

    public SaleDetailsProcessor(SaleService saleService) {
        super();
        this.saleService=saleService;
    }

    public Map<String, List<Sale>> process(SaleDetailsMessage message) throws InvalidSaleException,
                    InvalidMessageException {
        if (Objects.isNull(message)) {
            throw new InvalidMessageException(Constants.NULL_MSG_ERROR);
        }
        if (Objects.isNull(message.getProductType())) {
            throw new InvalidMessageException(Constants.NULL_PRODUCT_TYPE_ERROR);
        }
        Sale sale = new Sale(message.getProductType(), message.getPrice());
        saleService.save(sale);
        return SaleService.getSaleMap();
    }

}
