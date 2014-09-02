package com.xb.safe.dto;

public class Price {

    private Integer id;
    private Integer price;
    private Integer departmentId;
    private Integer modelId;
    private Integer rentPeriod;
    private Integer key;
    private Integer priceRent24;
    private Model model;

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

    public Model getModel() {
        return model;
    }

    public void setModel(Model model) {
        this.model = model;
    }

    @Override
    public String toString() {
        return "Price{" + "id=" + id + ", price=" + price + ", departmentId=" + departmentId + ", modelId=" + modelId + ", rentPeriod=" + rentPeriod + ", key=" + key + ", priceRent24=" + priceRent24 + ", model=" + model + '}';
    }

}
