package com.sid.message.proccessing.models;

import java.io.Serializable;

public class Sale implements Serializable {

    private String productType;
    private Long value;

    public Sale() {
    }

    public Sale(String productType, Long value) {
        super();
        this.productType = productType;
        this.value = value;
    }

    public String getProductType() {
        return productType;
    }

    public void setProductType(String productType) {
        this.productType = productType;
    }

    public Long getValue() {
        return value;
    }

    public void setValue(Long value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return "Sale [Product Type=" + productType + ", value=" + value + "]";
    }

}
