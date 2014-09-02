package com.xb.safe.dto;

public class Department {

    private Integer id;
    private String name;
    private String city;
    private String address;
    private String region;
    private Integer extId;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public Integer getExtId() {
        return extId;
    }

    public void setExtId(Integer extId) {
        this.extId = extId;
    }

    @Override
    public String toString() {
        return "Department{" + "id=" + id + ", name=" + name + ", city=" + city + ", address=" + address + ", region=" + region + ", extId=" + extId + '}';
    }

}
