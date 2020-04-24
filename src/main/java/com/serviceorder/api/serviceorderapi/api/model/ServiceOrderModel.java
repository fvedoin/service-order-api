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
    private OffsetDateTime startDate;
    private OffsetDateTime endDate;

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

    public OffsetDateTime getStartDate() {
        return startDate;
    }

    public void setStartDate(OffsetDateTime startDate) {
        this.startDate = startDate;
    }

    public OffsetDateTime getEndDate() {
        return endDate;
    }

    public void setEndDate(OffsetDateTime endDate) {
        this.endDate = endDate;
    }
}
