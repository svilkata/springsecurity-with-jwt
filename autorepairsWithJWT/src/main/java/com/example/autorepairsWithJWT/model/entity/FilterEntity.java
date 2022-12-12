package com.example.autorepairsWithJWT.model.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "filters")
public class FilterEntity extends BaseEntity{
    @Column(nullable = false)
    private String brand;
    @Column(nullable = false)
    private String model;
    @Column(nullable = false)
    private String modification;

    public FilterEntity() {
    }

    public String getBrand() {
        return brand;
    }

    public FilterEntity setBrand(String brand) {
        this.brand = brand;
        return this;
    }

    public String getModel() {
        return model;
    }

    public FilterEntity setModel(String model) {
        this.model = model;
        return this;
    }

    public String getModification() {
        return modification;
    }

    public FilterEntity setModification(String modification) {
        this.modification = modification;
        return this;
    }
}
