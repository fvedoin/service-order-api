package com.serviceorder.api.serviceorderapi.api.model;

import javax.validation.constraints.NotNull;

public class ClientIdInputModel {

    @NotNull
    private Long Id;

    public Long getId() {
        return Id;
    }

    public void setId(Long id) {
        Id = id;
    }
}
