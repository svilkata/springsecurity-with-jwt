package com.example.autorepairsWithJWT.model.dto.sparepart;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class RimResponse {
    private Long id;
    private String metalKind;
    private String inches;

    public RimResponse() {
    }

    public Long getId() {
        return id;
    }

    public RimResponse setId(Long id) {
        this.id = id;
        return this;
    }

    public String getMetalKind() {
        return metalKind;
    }

    public RimResponse setMetalKind(String metalKind) {
        this.metalKind = metalKind;
        return this;
    }

    public String getInches() {
        return inches;
    }

    public RimResponse setInches(String inches) {
        this.inches = inches;
        return this;
    }

    @Override
    public String toString() {
        return "RimResponse{" +
                "id=" + id +
                ", metalKind='" + metalKind + '\'' +
                ", inches='" + inches + '\'' +
                '}';
    }
}
