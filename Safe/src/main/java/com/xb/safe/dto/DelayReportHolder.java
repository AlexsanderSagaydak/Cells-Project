package com.xb.safe.dto;

public class DelayReportHolder {

    private Integer contractId;
    private Integer safeHeight;
    private Integer safeWidth;
    private Integer safeDepth;
    private Integer safeNum;
    private Integer delayDays;
    private String dateEnd;
    private String nameDepartment;
    private String clientName;
    private String clientSurname;
    private String clientPatronymic;
    private String clientPhone;
    private Double contractDebt;

    public Integer getContractId() {
        return contractId;
    }

    public void setContractId(Integer contractId) {
        this.contractId = contractId;
    }

    public Integer getSafeHeight() {
        return safeHeight;
    }

    public void setSafeHeight(Integer safeHeight) {
        this.safeHeight = safeHeight;
    }

    public Integer getSafeWidth() {
        return safeWidth;
    }

    public void setSafeWidth(Integer safeWidth) {
        this.safeWidth = safeWidth;
    }

    public Integer getSafeDepth() {
        return safeDepth;
    }

    public void setSafeDepth(Integer safeDepth) {
        this.safeDepth = safeDepth;
    }

    public Integer getSafeNum() {
        return safeNum;
    }

    public void setSafeNum(Integer safeNum) {
        this.safeNum = safeNum;
    }

    public Integer getDelayDays() {
        return delayDays;
    }

    public void setDelayDays(Integer delayDays) {
        this.delayDays = delayDays;
    }

    public String getDateEnd() {
        return dateEnd;
    }

    public void setDateEnd(String dateEnd) {
        this.dateEnd = dateEnd;
    }

    public String getNameDepartment() {
        return nameDepartment;
    }

    public void setNameDepartment(String nameDepartment) {
        this.nameDepartment = nameDepartment;
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

    public String getClientPhone() {
        return clientPhone;
    }

    public void setClientPhone(String clientPhone) {
        this.clientPhone = clientPhone;
    }

    public Double getContractDebt() {
        return contractDebt;
    }

    public void setContractDebt(Double contractDebt) {
        this.contractDebt = contractDebt;
    }

    @Override
    public String toString() {
        return "DelayReportHolder{" + "contractId=" + contractId + ", safeHeight=" + safeHeight + ", safeWidth=" + safeWidth + ", safeDepth=" + safeDepth + ", safeNum=" + safeNum + ", delayDays=" + delayDays + ", dateEnd=" + dateEnd + ", nameDepartment=" + nameDepartment + ", clientName=" + clientName + ", clientSurname=" + clientSurname + ", clientPatronymic=" + clientPatronymic + ", clientPhone=" + clientPhone + ", contractDebt=" + contractDebt + '}';
    }

}
