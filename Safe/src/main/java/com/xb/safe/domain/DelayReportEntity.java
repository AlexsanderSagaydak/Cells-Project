package com.xb.safe.domain;

import java.io.Serializable;
import javax.persistence.*;
import org.hibernate.annotations.Subselect;

@Entity
@Subselect("")
public class DelayReportEntity implements Serializable {
    @Id
    @Column(name = "contract_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer contractId;
    @Column(name = "depth")
    private Integer safeDepth;
    @Column(name = "width")
    private Integer safeWidth;
    @Column(name = "height")
    private Integer safeHeight;
    @Column(name = "delay_days")
    private Integer delayDays;
    @Column(name = "phone_m1")
    private String clientPhone;
    @Column(name = "patronymic")
    private String clientPatronymic;
    @Column(name = "surname")
    private String clientSurname;
    @Column(name = "client_name")
    private String clientName;
    @Column(name = "safe_num")
    private Integer safeNum;
    @Column(name = "date_end")
    private String dateEnd;
    @Column(name = "sum_delay")
    private Double contractDebt;
    @Column(name = "name")
    private String nameDepartment;

    public Integer getContractId() {
        return contractId;
    }

    public void setContractId(Integer contractId) {
        this.contractId = contractId;
    }

    public Integer getSafeDepth() {
        return safeDepth;
    }

    public void setSafeDepth(Integer safeDepth) {
        this.safeDepth = safeDepth;
    }

    public Integer getSafeWidth() {
        return safeWidth;
    }

    public void setSafeWidth(Integer safeWidth) {
        this.safeWidth = safeWidth;
    }

    public Integer getSafeHeight() {
        return safeHeight;
    }

    public void setSafeHeight(Integer safeHeight) {
        this.safeHeight = safeHeight;
    }

    public Integer getDelayDays() {
        return delayDays;
    }

    public void setDelayDays(Integer delayDays) {
        this.delayDays = delayDays;
    }

    public String getClientPhone() {
        return clientPhone;
    }

    public void setClientPhone(String clientPhone) {
        this.clientPhone = clientPhone;
    }

    public String getClientPatronymic() {
        return clientPatronymic;
    }

    public void setClientPatronymic(String clientPatronymic) {
        this.clientPatronymic = clientPatronymic;
    }

    public String getClientSurname() {
        return clientSurname;
    }

    public void setClientSurname(String clientSurname) {
        this.clientSurname = clientSurname;
    }

    public String getClientName() {
        return clientName;
    }

    public void setClientName(String clientName) {
        this.clientName = clientName;
    }

    public Integer getSafeNum() {
        return safeNum;
    }

    public void setSafeNum(Integer safeNum) {
        this.safeNum = safeNum;
    }

    public String getDateEnd() {
        return dateEnd;
    }

    public void setDateEnd(String dateEnd) {
        this.dateEnd = dateEnd;
    }

    public Double getContractDebt() {
        return contractDebt;
    }

    public void setContractDebt(Double contractDebt) {
        this.contractDebt = contractDebt;
    }

    public String getNameDepartment() {
        return nameDepartment;
    }

    public void setNameDepartment(String nameDepartment) {
        this.nameDepartment = nameDepartment;
    }

}
