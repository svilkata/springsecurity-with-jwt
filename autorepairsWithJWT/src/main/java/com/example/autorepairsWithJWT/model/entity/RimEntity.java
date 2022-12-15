package com.example.autorepairsWithJWT.model.entity;

import com.example.autorepairsWithJWT.model.dto.sparepart.RimRequest;

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
        return this.metalKind;
    }

    public RimEntity setMetalKind(String metalKind) {
        this.metalKind = metalKind;
        return this;
    }

    public String getInches() {
        return this.inches;
    }

    public RimEntity setInches(String inches) {
        this.inches = inches;
        return this;
    }

    @Override
    public RimEntity setId(Long id) {
        super.setId(id);
        return this;
    }

    public boolean equalsToRequest(RimRequest rimRequest) {
        return rimRequest.getMetalKind().equals(this.metalKind) && rimRequest.getInches().equals(this.inches);
    }
}
