package com.xb.safe.domain;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "price")
public class PriceEntity implements Serializable {
    @Id
    @Column(name = "price_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(name = "price")
    private Integer price;
    @Column(name = "id_dep")
    private Integer departmentId;
    @Column(name = "id_model")
    private Integer modelId;
    @Column(name = "month_rent")
    private Integer rentPeriod;
    @Column(name = "key")
    private Integer key;
    @Column(name = "price_24h")
    private Integer priceRent24;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public Integer getDepartmentId() {
        return departmentId;
    }

    public void setDepartmentId(Integer departmentId) {
        this.departmentId = departmentId;
    }

    public Integer getModelId() {
        return modelId;
    }

    public void setModelId(Integer modelId) {
        this.modelId = modelId;
    }

    public Integer getRentPeriod() {
        return rentPeriod;
    }

    public void setRentPeriod(Integer rentPeriod) {
        this.rentPeriod = rentPeriod;
    }

    public Integer getKey() {
        return key;
    }

    public void setKey(Integer key) {
        this.key = key;
    }

    public Integer getPriceRent24() {
        return priceRent24;
    }

    public void setPriceRent24(Integer priceRent24) {
        this.priceRent24 = priceRent24;
    }

}
