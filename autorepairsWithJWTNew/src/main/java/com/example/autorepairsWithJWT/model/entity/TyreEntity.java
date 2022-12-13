package com.example.autorepairsWithJWT.model.entity;

import com.example.autorepairsWithJWT.model.enums.TyreKindEnum;

import javax.persistence.*;

@Entity
@Table(name = "tyres")
public class TyreEntity extends BaseEntity {
    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private TyreKindEnum tyreKind;
    @Column(nullable = false)
    private String brand;
    @Column(nullable = false)
    private String width;
    @Column(nullable = false)
    private String height;
    @Column(nullable = false)
    private String inches;
    @Column(nullable = false)
    private String flat;

    public TyreEntity() {
    }

    public TyreKindEnum getTyreKind() {
        return tyreKind;
    }

    public TyreEntity setTyreKind(TyreKindEnum tyreKind) {
        this.tyreKind = tyreKind;
        return this;
    }

    public String getBrand() {
        return brand;
    }

    public TyreEntity setBrand(String brand) {
        this.brand = brand;
        return this;
    }

    public String getWidth() {
        return width;
    }

    public TyreEntity setWidth(String width) {
        this.width = width;
        return this;
    }

    public String getHeight() {
        return height;
    }

    public TyreEntity setHeight(String height) {
        this.height = height;
        return this;
    }

    public String getInches() {
        return inches;
    }

    public TyreEntity setInches(String inches) {
        this.inches = inches;
        return this;
    }

    public String getFlat() {
        return flat;
    }

    public TyreEntity setFlat(String flat) {
        this.flat = flat;
        return this;
    }
}
