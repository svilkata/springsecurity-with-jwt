package com.example.autorepairsWithJWT.model.dto.sparepart;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class RimCreatedModifiedResponseJsonDTO {
    private Long id;
    private String metalKind;
    private String inches;

    public RimCreatedModifiedResponseJsonDTO() {
    }

    public Long getId() {
        return id;
    }

    public RimCreatedModifiedResponseJsonDTO setId(Long id) {
        this.id = id;
        return this;
    }

    public String getMetalKind() {
        return metalKind;
    }

    public RimCreatedModifiedResponseJsonDTO setMetalKind(String metalKind) {
        this.metalKind = metalKind;
        return this;
    }

    public String getInches() {
        return inches;
    }

    public RimCreatedModifiedResponseJsonDTO setInches(String inches) {
        this.inches = inches;
        return this;
    }
}
