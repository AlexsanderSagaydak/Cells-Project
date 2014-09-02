package com.xb.safe.dto;

import java.util.List;

public class ModelForXml extends Model {

    private List<Price> priceList;
    private List<Safe> safelist;

    public List<Price> getPriceList() {
        return priceList;
    }

    public void setPriceList(List<Price> priceList) {
        this.priceList = priceList;
    }

    public List<Safe> getSafelist() {
        return safelist;
    }

    public void setSafelist(List<Safe> safelist) {
        this.safelist = safelist;
    }

    @Override
    public String toString() {
        return "ModelForXml{" + "priceList=" + priceList + ", safelist=" + safelist + '}';
    }

}
