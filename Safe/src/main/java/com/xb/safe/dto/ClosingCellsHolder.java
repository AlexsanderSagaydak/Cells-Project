package com.xb.safe.dto;

public class ClosingCellsHolder {

    private Integer contractId;
    private Integer safePrice;
    private String clientName;
    private String clientSurname;
    private String clientPatronymic;
    private String safeNum;
    private String clientPhone;
    private String dateStart;
    private String dateEnd;

    public Integer getContractId() {
        return contractId;
    }

    public void setContractId(Integer contractId) {
        this.contractId = contractId;
    }

    public Integer getSafePrice() {
        return safePrice;
    }

    public void setSafePrice(Integer safePrice) {
        this.safePrice = safePrice;
    }

    public String getClientName() {
        return clientName;
    }

    public void setClientName(String clientName) {
        this.clientName = clientName;
    }

    public String getClientSurname() {
        return clientSurname;
    }

    public void setClientSurname(String clientSurname) {
        this.clientSurname = clientSurname;
    }

    public String getClientPatronymic() {
        return clientPatronymic;
    }

    public void setClientPatronymic(String clientPatronymic) {
        this.clientPatronymic = clientPatronymic;
    }

    public String getSafeNum() {
        return safeNum;
    }

    public void setSafeNum(String safeNum) {
        this.safeNum = safeNum;
    }

    public String getClientPhone() {
        return clientPhone;
    }

    public void setClientPhone(String clientPhone) {
        this.clientPhone = clientPhone;
    }

    public String getDateStart() {
        return dateStart;
    }

    public void setDateStart(String dateStart) {
        this.dateStart = dateStart;
    }

    public String getDateEnd() {
        return dateEnd;
    }

    public void setDateEnd(String dateEnd) {
        this.dateEnd = dateEnd;
    }

    @Override
    public String toString() {
        return "ClosingCellsHolder{" + "contractId=" + contractId + ", safePrice=" + safePrice + ", clientName=" + clientName + ", clientSurname=" + clientSurname + ", clientPatronymic=" + clientPatronymic + ", safeNum=" + safeNum + ", clientPhone=" + clientPhone + ", dateStart=" + dateStart + ", dateEnd=" + dateEnd + '}';
    }
}
