package com.xb.safe.domain;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "contract")
public class ContractEntity implements Serializable {

    @Id
    @Column(name = "contract_id", updatable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "safe_size", updatable = false)
    private Integer safeSize;

    @Column(name = "safe_num", updatable = false)
    private Integer safeNumber;

    @Column(name = "month_rent")
    private Integer monthRent;

    @Column(name = "price")
    private Integer price;

    @Column(name = "contract_status", updatable = false)
    private Integer contractStatus;

    @Column(name = "key", updatable = false)
    private Integer key;

    @Column(name = "date_start", updatable = false)
    private String dateContractStart;

    @Column(name = "date_end")
    private String dateContractEnd;
    
    @Column(name = "date_close")
    private String dateContractClose;

    @Column(name = "price_rent_24", updatable = false)
    private Integer priceRentPerDay;

    @Column(name = "client_id1", updatable = false)
    private Integer clientId1;

    @Column(name = "client_id2", updatable = false)
    private Integer clientId2;

    @Column(name = "dept_id", updatable = false)
    private Integer departmentId;

    @Column(name = "safe_id", updatable = false)
    private Integer safeId;

    @Column(name = "contract_number", updatable = false)
    private String numberContract;

//    @Column(name = "dept_addr", updatable = false)
//    private String departmentName;
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public Integer getContractStatus() {
        return contractStatus;
    }

    public void setContractStatus(Integer contractStatus) {
        this.contractStatus = contractStatus;
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

    public Integer getDepartmentId() {
        return departmentId;
    }

    public void setDepartmentId(Integer departmentId) {
        this.departmentId = departmentId;
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

}
