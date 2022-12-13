package com.example.autorepairsWithJWT.model.dto.sparepart;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class RimCreatedModifiedResponse {
    private Long id;
    private String metalKind;
    private String inches;

    public RimCreatedModifiedResponse() {
    }

    public Long getId() {
        return id;
    }

    public RimCreatedModifiedResponse setId(Long id) {
        this.id = id;
        return this;
    }

    public String getMetalKind() {
        return metalKind;
    }

    public RimCreatedModifiedResponse setMetalKind(String metalKind) {
        this.metalKind = metalKind;
        return this;
    }

    public String getInches() {
        return inches;
    }

    public RimCreatedModifiedResponse setInches(String inches) {
        this.inches = inches;
        return this;
    }
}
