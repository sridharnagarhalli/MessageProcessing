package com.sid.message.proccessing.services;

import com.sid.message.proccessing.exceptions.InvalidSaleException;
import com.sid.message.proccessing.models.Sale;
import com.sid.message.proccessing.utils.Constants;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

public class SaleService {

    static Map<String, List<Sale>> saleMap = new HashMap();

    public List<Sale> findByProductType(String productType) {
        return Objects.nonNull(saleMap.get(productType)) ? saleMap.get(productType) : new ArrayList();
    }

    public Sale save(Sale sale) throws InvalidSaleException {
        if (Objects.isNull(sale)) {
            throw new InvalidSaleException(Constants.NULL_SALE_ERROR);
        }
        if (Objects.isNull(sale.getProductType()) || sale.getProductType().isEmpty()) {
            throw new InvalidSaleException(Constants.NULL_PRODUCT_TYPE_ERROR);
        }
        List<Sale> productSales = findByProductType(sale.getProductType());
        productSales.add(sale);
        saleMap.put(sale.getProductType(), productSales);
        return sale;
    }

    public static Map<String, List<Sale>> getSaleMap() {
        return saleMap;
    }
}
