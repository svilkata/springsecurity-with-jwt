package com.example.autorepairsWithJWT.model.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "rims")
public class RimEntity extends BaseEntity{
    @Column(nullable = false, name = "metal_kind")
    private String metalKind;

    @Column(nullable = false)
    private String inches;

    public RimEntity() {
    }

    public String getMetalKind() {
        return metalKind;
    }

    public RimEntity setMetalKind(String metalKind) {
        this.metalKind = metalKind;
        return this;
    }

    public String getInches() {
        return inches;
    }

    public RimEntity setInches(String inches) {
        this.inches = inches;
        return this;
    }
}
