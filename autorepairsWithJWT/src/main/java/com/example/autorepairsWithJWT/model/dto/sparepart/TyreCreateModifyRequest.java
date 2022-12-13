package com.example.autorepairsWithJWT.model.dto.sparepart;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class TyreCreateModifyRequest {
    private String tyreKind;
    private String brand;
    private String width;
    private String height;
    private String inches;
    private String flat;

    public TyreCreateModifyRequest() {
    }

    public String getTyreKind() {
        return tyreKind;
    }

    public TyreCreateModifyRequest setTyreKind(String tyreKind) {
        this.tyreKind = tyreKind;
        return this;
    }

    public String getBrand() {
        return brand;
    }

    public TyreCreateModifyRequest setBrand(String brand) {
        this.brand = brand;
        return this;
    }

    public String getWidth() {
        return width;
    }

    public TyreCreateModifyRequest setWidth(String width) {
        this.width = width;
        return this;
    }

    public String getHeight() {
        return height;
    }

    public TyreCreateModifyRequest setHeight(String height) {
        this.height = height;
        return this;
    }

    public String getInches() {
        return inches;
    }

    public TyreCreateModifyRequest setInches(String inches) {
        this.inches = inches;
        return this;
    }

    public String getFlat() {
        return flat;
    }

    public TyreCreateModifyRequest setFlat(String flat) {
        this.flat = flat;
        return this;
    }
}