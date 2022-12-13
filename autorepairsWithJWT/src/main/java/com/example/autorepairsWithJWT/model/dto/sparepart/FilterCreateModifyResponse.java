package com.example.autorepairsWithJWT.model.dto.sparepart;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class FilterCreateModifyResponse {
    private Long id;
    private String brand;
    private String model;
    private String modification;

    public FilterCreateModifyResponse() {
    }

    public Long getId() {
        return id;
    }

    public FilterCreateModifyResponse setId(Long id) {
        this.id = id;
        return this;
    }

    public String getBrand() {
        return brand;
    }

    public FilterCreateModifyResponse setBrand(String brand) {
        this.brand = brand;
        return this;
    }

    public String getModel() {
        return model;
    }

    public FilterCreateModifyResponse setModel(String model) {
        this.model = model;
        return this;
    }

    public String getModification() {
        return modification;
    }

    public FilterCreateModifyResponse setModification(String modification) {
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
