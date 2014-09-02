package com.xb.safe.dto;

import java.util.List;

public class Departments extends Department {

    private String region;

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public List<ModelForXml> getModelList() {
        return modelList;
    }

    public void setModelList(List<ModelForXml> modelList) {
        this.modelList = modelList;
    }
    private List<ModelForXml> modelList;

    @Override
    public String toString() {
        return "Departments{" + "region=" + region + ", modelList=" + modelList + '}';
    }

}
