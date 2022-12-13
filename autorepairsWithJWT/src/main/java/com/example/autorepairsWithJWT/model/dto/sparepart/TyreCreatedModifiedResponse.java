package com.example.autorepairsWithJWT.model.dto.sparepart;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class TyreCreatedModifiedResponse {
    private Long id;
    private String tyreKind;
    private String brand;
    private String width;
    private String height;
    private String inches;
    private String flat;

    public TyreCreatedModifiedResponse() {
    }

    public String getTyreKind() {
        return tyreKind;
    }

    public TyreCreatedModifiedResponse setTyreKind(String tyreKind) {
        this.tyreKind = tyreKind;
        return this;
    }

    public String getBrand() {
        return brand;
    }

    public TyreCreatedModifiedResponse setBrand(String brand) {
        this.brand = brand;
        return this;
    }

    public String getWidth() {
        return width;
    }

    public TyreCreatedModifiedResponse setWidth(String width) {
        this.width = width;
        return this;
    }

    public String getHeight() {
        return height;
    }

    public TyreCreatedModifiedResponse setHeight(String height) {
        this.height = height;
        return this;
    }

    public String getInches() {
        return inches;
    }

    public TyreCreatedModifiedResponse setInches(String inches) {
        this.inches = inches;
        return this;
    }

    public String getFlat() {
        return flat;
    }

    public TyreCreatedModifiedResponse setFlat(String flat) {
        this.flat = flat;
        return this;
    }

    public Long getId() {
        return id;
    }

    public TyreCreatedModifiedResponse setId(Long id) {
        this.id = id;
        return this;
    }
}
