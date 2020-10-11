package com.sid.message.proccessing.models.messages;

import java.io.Serializable;

public class GenericMessage implements Serializable {
    private Integer messageType;
    private String productType;

    public GenericMessage(final Integer messageType, final String productType) {
        super();
        this.messageType = messageType;
        this.productType = productType;
    }

    public Integer getMessageType() {
        return messageType;
    }

    public void setMessageType(Integer messageType) {
        this.messageType = messageType;
    }

    public String getProductType() {
        return productType;
    }

    public void setProductType(String productType) {
        this.productType = productType;
    }

}
