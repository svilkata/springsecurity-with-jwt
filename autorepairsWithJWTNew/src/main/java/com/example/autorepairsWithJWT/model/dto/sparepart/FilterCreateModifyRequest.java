package com.example.autorepairsWithJWT.model.dto.sparepart;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class FilterCreateModifyRequest {
    private String brand;
    private String model;
    private String modification;

    public FilterCreateModifyRequest() {
    }

    public String getBrand() {
        return brand;
    }

    public FilterCreateModifyRequest setBrand(String brand) {
        this.brand = brand;
        return this;
    }

    public String getModel() {
        return model;
    }

    public FilterCreateModifyRequest setModel(String model) {
        this.model = model;
        return this;
    }

    public String getModification() {
        return modification;
    }

    public FilterCreateModifyRequest setModification(String modification) {
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
