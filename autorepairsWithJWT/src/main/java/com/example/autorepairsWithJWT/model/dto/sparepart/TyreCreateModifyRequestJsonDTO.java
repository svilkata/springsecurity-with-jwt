package com.example.autorepairsWithJWT.model.dto.sparepart;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class TyreCreateModifyRequestJsonDTO {
    private String tyreKind;
    private String brand;
    private String width;
    private String height;
    private String inches;
    private String flat;

    public TyreCreateModifyRequestJsonDTO() {
    }

    public String getTyreKind() {
        return tyreKind;
    }

    public TyreCreateModifyRequestJsonDTO setTyreKind(String tyreKind) {
        this.tyreKind = tyreKind;
        return this;
    }

    public String getBrand() {
        return brand;
    }

    public TyreCreateModifyRequestJsonDTO setBrand(String brand) {
        this.brand = brand;
        return this;
    }

    public String getWidth() {
        return width;
    }

    public TyreCreateModifyRequestJsonDTO setWidth(String width) {
        this.width = width;
        return this;
    }

    public String getHeight() {
        return height;
    }

    public TyreCreateModifyRequestJsonDTO setHeight(String height) {
        this.height = height;
        return this;
    }

    public String getInches() {
        return inches;
    }

    public TyreCreateModifyRequestJsonDTO setInches(String inches) {
        this.inches = inches;
        return this;
    }

    public String getFlat() {
        return flat;
    }

    public TyreCreateModifyRequestJsonDTO setFlat(String flat) {
        this.flat = flat;
        return this;
    }
}
