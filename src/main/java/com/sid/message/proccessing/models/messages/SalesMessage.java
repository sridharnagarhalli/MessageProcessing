package com.sid.message.proccessing.models.messages;

public class SalesMessage extends GenericMessage {
    private Long price;
    private Integer numberOfSales;

    public SalesMessage(final String productType, final Integer numberOfSales, final Long price) {
        super(2, productType);
        this.numberOfSales = numberOfSales;
        this.price = price;
    }

    public Long getPrice() {
        return price;
    }

    public void setPrice(Long price) {
        this.price = price;
    }

    public Integer getNumberOfSales() {
        return numberOfSales;
    }

    public void setNumberOfSales(Integer numberOfSales) {
        this.numberOfSales = numberOfSales;
    }

}
