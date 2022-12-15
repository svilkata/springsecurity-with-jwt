package com.example.autorepairsWithJWT.model.dto.sparepart;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class TyreResponse {
    private Long id;
    private String tyreKind;
    private String brand;
    private String width;
    private String height;
    private String inches;
    private String flat;

    public TyreResponse() {
    }

    public String getTyreKind() {
        return tyreKind;
    }

    public TyreResponse setTyreKind(String tyreKind) {
        this.tyreKind = tyreKind;
        return this;
    }

    public String getBrand() {
        return brand;
    }

    public TyreResponse setBrand(String brand) {
        this.brand = brand;
        return this;
    }

    public String getWidth() {
        return width;
    }

    public TyreResponse setWidth(String width) {
        this.width = width;
        return this;
    }

    public String getHeight() {
        return height;
    }

    public TyreResponse setHeight(String height) {
        this.height = height;
        return this;
    }

    public String getInches() {
        return inches;
    }

    public TyreResponse setInches(String inches) {
        this.inches = inches;
        return this;
    }

    public String getFlat() {
        return flat;
    }

    public TyreResponse setFlat(String flat) {
        this.flat = flat;
        return this;
    }

    public Long getId() {
        return id;
    }

    public TyreResponse setId(Long id) {
        this.id = id;
        return this;
    }

    @Override
    public String toString() {
        return "TyreResponse{" +
                "id=" + id +
                ", tyreKind='" + tyreKind + '\'' +
                ", brand='" + brand + '\'' +
                ", width='" + width + '\'' +
                ", height='" + height + '\'' +
                ", inches='" + inches + '\'' +
                ", flat='" + flat + '\'' +
                '}';
    }
}
