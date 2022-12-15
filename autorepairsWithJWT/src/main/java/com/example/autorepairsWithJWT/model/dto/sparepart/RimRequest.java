package com.example.autorepairsWithJWT.model.dto.sparepart;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class RimRequest {
    private String metalKind;
    private String inches;

    public RimRequest() {
    }

    public String getMetalKind() {
        return metalKind;
    }

    public RimRequest setMetalKind(String metalKind) {
        this.metalKind = metalKind;
        return this;
    }

    public String getInches() {
        return inches;
    }

    public RimRequest setInches(String inches) {
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
