package com.xb.safe.dto;

public class Contract {

    private Integer id;
    private Integer departmentId;
    private Integer safeSize;
    private Integer safeNumber;
    private Integer monthRent;
    private Integer contractStatus;
    private Integer price;
    private Integer key;
    private String dateContractStart;
    private String dateContractEnd;
    private String dateContractClose;
    private Integer priceRentPerDay;
    private Integer clientId1;
    private Integer clientId2;
    private Client client1;
    private Client client2;
    private Integer safeId;
    private String numberContract;
    private String departmentName;
    private Model model;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getDepartmentId() {
        return departmentId;
    }

    public void setDepartmentId(Integer departmentId) {
        this.departmentId = departmentId;
    }

    public Integer getSafeSize() {
        return safeSize;
    }

    public void setSafeSize(Integer safeSize) {
        this.safeSize = safeSize;
    }

    public Integer getSafeNumber() {
        return safeNumber;
    }

    public void setSafeNumber(Integer safeNumber) {
        this.safeNumber = safeNumber;
    }

    public Integer getMonthRent() {
        return monthRent;
    }

    public void setMonthRent(Integer monthRent) {
        this.monthRent = monthRent;
    }

    public Integer getContractStatus() {
        return contractStatus;
    }

    public void setContractStatus(Integer contractStatus) {
        this.contractStatus = contractStatus;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public Integer getKey() {
        return key;
    }

    public void setKey(Integer key) {
        this.key = key;
    }

    public String getDateContractStart() {
        return dateContractStart;
    }

    public void setDateContractStart(String dateContractStart) {
        this.dateContractStart = dateContractStart;
    }

    public String getDateContractEnd() {
        return dateContractEnd;
    }

    public void setDateContractEnd(String dateContractEnd) {
        this.dateContractEnd = dateContractEnd;
    }

    public String getDateContractClose() {
        return dateContractClose;
    }

    public void setDateContractClose(String dateContractClose) {
        this.dateContractClose = dateContractClose;
    }

    public Integer getPriceRentPerDay() {
        return priceRentPerDay;
    }

    public void setPriceRentPerDay(Integer priceRentPerDay) {
        this.priceRentPerDay = priceRentPerDay;
    }

    public Integer getClientId1() {
        return clientId1;
    }

    public void setClientId1(Integer clientId1) {
        this.clientId1 = clientId1;
    }

    public Integer getClientId2() {
        return clientId2;
    }

    public void setClientId2(Integer clientId2) {
        this.clientId2 = clientId2;
    }

    public Client getClient1() {
        return client1;
    }

    public void setClient1(Client client1) {
        this.client1 = client1;
    }

    public Client getClient2() {
        return client2;
    }

    public void setClient2(Client client2) {
        this.client2 = client2;
    }

    public Integer getSafeId() {
        return safeId;
    }

    public void setSafeId(Integer safeId) {
        this.safeId = safeId;
    }

    public String getNumberContract() {
        return numberContract;
    }

    public void setNumberContract(String numberContract) {
        this.numberContract = numberContract;
    }

    public String getDepartmentName() {
        return departmentName;
    }

    public void setDepartmentName(String departmentName) {
        this.departmentName = departmentName;
    }

    public Model getModel() {
        return model;
    }

    public void setModel(Model model) {
        this.model = model;
    }

    @Override
    public String toString() {
        return "Contract{" + "id=" + id + ", departmentId=" + departmentId + ", safeSize=" + safeSize + ", safeNumber=" + safeNumber + ", monthRent=" + monthRent + ", contractStatus=" + contractStatus + ", price=" + price + ", key=" + key + ", dateContractStart=" + dateContractStart + ", dateContractEnd=" + dateContractEnd + ", dateContractClose=" + dateContractClose + ", priceRentPerDay=" + priceRentPerDay + ", clientId1=" + clientId1 + ", clientId2=" + clientId2 + ", client1=" + client1 + ", client2=" + client2 + ", safeId=" + safeId + ", numberContract=" + numberContract + ", departmentName=" + departmentName + ", model=" + model + '}';
    }
}
