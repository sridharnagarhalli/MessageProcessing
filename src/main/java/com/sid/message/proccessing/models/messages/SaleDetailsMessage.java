package com.sid.message.proccessing.models.messages;

public class SaleDetailsMessage extends GenericMessage {

    private Long price;

    public SaleDetailsMessage(final String productType, final Long price) {
        super(1, productType);
        this.price = price;
    }

    public Long getPrice() {
        return price;
    }

    public void setPrice(Long price) {
        this.price = price;
    }

}
