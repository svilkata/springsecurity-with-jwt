package com.example.autorepairsWithJWT.model.dto.sparepart;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class RimCreateModifyRequest {
    private String metalKind;
    private String inches;

    public RimCreateModifyRequest() {
    }

    public String getMetalKind() {
        return metalKind;
    }

    public RimCreateModifyRequest setMetalKind(String metalKind) {
        this.metalKind = metalKind;
        return this;
    }

    public String getInches() {
        return inches;
    }

    public RimCreateModifyRequest setInches(String inches) {
        this.inches = inches;
        return this;
    }

    @Override
    public String toString() {
        return "RimJsonDTO{" +
                "metalKind='" + metalKind + '\'' +
                ", inches='" + inches + '\'' +
                '}';
    }
}
