package com.sid.message.proccessing.services;

import com.sid.message.proccessing.models.Sale;
import com.sid.message.proccessing.models.reports.SalesReport;

import java.util.ArrayList;
import java.util.List;

public class TotalSalesReportService {

    public List<SalesReport> getTotalPriceByProductReport() {
        List<SalesReport> report = new ArrayList();
        SaleService.getSaleMap().keySet().forEach(productType -> {
            List<Sale> productSales = SaleService.getSaleMap().get(productType);
            SalesReport reportItem = new SalesReport();
            reportItem.setProductType(productType);
            reportItem.setNumberOfSales(productSales.size());
            reportItem.setTotalPrice(calculateTotalPrice(productSales));
            report.add(reportItem);
        });
        return report;
    }

    private Long calculateTotalPrice(List<Sale> productSales) {
        long totalPrice = 0;
        for (Sale sale : productSales) {
            totalPrice = totalPrice + sale.getValue();
        }
        return totalPrice;
    }
}
