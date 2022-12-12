package com.example.autorepairsWithJWT.model.dto.sparepart;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class RimCreateModifyRequestJsonDTO {
    private String metalKind;
    private String inches;

    public RimCreateModifyRequestJsonDTO() {
    }

    public String getMetalKind() {
        return metalKind;
    }

    public RimCreateModifyRequestJsonDTO setMetalKind(String metalKind) {
        this.metalKind = metalKind;
        return this;
    }

    public String getInches() {
        return inches;
    }

    public RimCreateModifyRequestJsonDTO setInches(String inches) {
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
