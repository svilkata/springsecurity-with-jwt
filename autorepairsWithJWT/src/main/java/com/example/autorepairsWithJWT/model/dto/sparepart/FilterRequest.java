package com.example.autorepairsWithJWT.model.dto.sparepart;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class FilterRequest {
    private String brand;
    private String model;
    private String modification;

    public FilterRequest() {
    }

    public String getBrand() {
        return brand;
    }

    public FilterRequest setBrand(String brand) {
        this.brand = brand;
        return this;
    }

    public String getModel() {
        return model;
    }

    public FilterRequest setModel(String model) {
        this.model = model;
        return this;
    }

    public String getModification() {
        return modification;
    }

    public FilterRequest setModification(String modification) {
        this.modification = modification;
        return this;
    }

    @Override
    public String toString() {
        return "FilterJsonDTO{" +
                "brand='" + brand + '\'' +
                ", model='" + model + '\'' +
                ", modification='" + modification + '\'' +
                '}';
    }
}
