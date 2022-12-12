package com.example.autorepairsWithJWT.model.dto.sparepart;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class FilterCreateModifyRequestJsonDTO {
    private String brand;
    private String model;
    private String modification;

    public FilterCreateModifyRequestJsonDTO() {
    }

    public String getBrand() {
        return brand;
    }

    public FilterCreateModifyRequestJsonDTO setBrand(String brand) {
        this.brand = brand;
        return this;
    }

    public String getModel() {
        return model;
    }

    public FilterCreateModifyRequestJsonDTO setModel(String model) {
        this.model = model;
        return this;
    }

    public String getModification() {
        return modification;
    }

    public FilterCreateModifyRequestJsonDTO setModification(String modification) {
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
