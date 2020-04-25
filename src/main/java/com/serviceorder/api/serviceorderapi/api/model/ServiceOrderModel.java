package com.serviceorder.api.serviceorderapi.api.model;

import com.serviceorder.api.serviceorderapi.domain.model.ServiceOrderStatus;

import java.math.BigDecimal;
import java.time.OffsetDateTime;

public class ServiceOrderModel {

    private Long id;
    private ClientSummaryModel client;
    private String description;
    private BigDecimal price;
    private ServiceOrderStatus status;
    private OffsetDateTime openingDate;
    private OffsetDateTime closingDate;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public ClientSummaryModel getClient() {
        return client;
    }

    public void setClient(ClientSummaryModel client) {
        this.client = client;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public ServiceOrderStatus getStatus() {
        return status;
    }

    public void setStatus(ServiceOrderStatus status) {
        this.status = status;
    }

    public OffsetDateTime getOpeningDate() {
        return openingDate;
    }

    public void setOpeningDate(OffsetDateTime openingDate) {
        this.openingDate = openingDate;
    }

    public OffsetDateTime getClosingDate() {
        return closingDate;
    }

    public void setClosingDate(OffsetDateTime closingDate) {
        this.closingDate = closingDate;
    }
}
