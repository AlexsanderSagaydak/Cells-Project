package com.xb.safe.domain;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import org.hibernate.annotations.Subselect;

@Entity
@Subselect("")
public class ClosingCellsEntity implements Serializable {
    @Id
    @Column(name = "contract_Id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer contractId;
    @Column(name = "surname")
    private String clientSurname;
    @Column(name = "name")
    private String clientName;
    @Column(name = "patronymic")
    private String clientPatronymic;
    @Column(name = "phone_m1")
    private String clientPhone;
    @Column(name = "safe_num")
    private Integer safeNum;
    @Column(name = "date_start")
    private String dateStart;
    @Column(name = "date_end")
    private String dateEnd;
    @Column(name = "price")
    private Integer safePrice;

    public Integer getContractId() {
        return contractId;
    }

    public void setContractId(Integer contractId) {
        this.contractId = contractId;
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

    public Integer getSafeNum() {
        return safeNum;
    }

    public void setSafeNum(Integer safeNum) {
        this.safeNum = safeNum;
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

    public Integer getSafePrice() {
        return safePrice;
    }

    public void setSafePrice(Integer safePrice) {
        this.safePrice = safePrice;
    }

}
