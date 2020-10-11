package com.sid.message.proccessing.services;

import com.sid.message.proccessing.models.reports.SaleAdjustment;
import com.sid.message.proccessing.exceptions.InvalidAdjustmentException;
import com.sid.message.proccessing.utils.Constants;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

public class SaleAdjustmentsReportService {
    private static Map<String, List<SaleAdjustment>> adjustmentMap = new HashMap<String, List<SaleAdjustment>>();

    public List<SaleAdjustment> saveAdjustment(SaleAdjustment adjustment, String productType)
                    throws InvalidAdjustmentException {
        if (Objects.isNull(adjustment)) {
            throw new InvalidAdjustmentException(Constants.NULL_ADJUSTMENT_ERROR);
        }
        if (Objects.isNull(productType)) {
            throw new InvalidAdjustmentException(Constants.NULL_PRODUCT_TYPE_ERROR);
        }
        List<SaleAdjustment> adjustments = Objects.nonNull(adjustmentMap.get(productType))
                        ? adjustmentMap.get(productType)
                        : new ArrayList<SaleAdjustment>();
        adjustments.add(adjustment);
        adjustmentMap.put(productType, adjustments);
        return adjustments;
    }

    public static Map<String, List<SaleAdjustment>> getAdjustmentMap() {
        return adjustmentMap;
    }
}
