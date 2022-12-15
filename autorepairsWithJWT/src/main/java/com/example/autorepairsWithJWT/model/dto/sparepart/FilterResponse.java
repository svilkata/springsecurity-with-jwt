package com.example.autorepairsWithJWT.model.dto.sparepart;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class FilterResponse {
    private Long id;
    private String brand;
    private String model;
    private String modification;

    public FilterResponse() {
    }

    public Long getId() {
        return id;
    }

    public FilterResponse setId(Long id) {
        this.id = id;
        return this;
    }

    public String getBrand() {
        return brand;
    }

    public FilterResponse setBrand(String brand) {
        this.brand = brand;
        return this;
    }

    public String getModel() {
        return model;
    }

    public FilterResponse setModel(String model) {
        this.model = model;
        return this;
    }

    public String getModification() {
        return modification;
    }

    public FilterResponse setModification(String modification) {
        this.modification = modification;
        return this;
    }

    @Override
    public String toString() {
        return "FilterResponse{" +
                "id=" + id +
                ", brand='" + brand + '\'' +
                ", model='" + model + '\'' +
                ", modification='" + modification + '\'' +
                '}';
    }
}
