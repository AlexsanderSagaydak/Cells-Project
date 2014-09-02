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
public class FilingCellsEntity implements Serializable {
    @Id
    @Column(name = "dept_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer departmentId;
    @Column(name = "name")
    private String departmentName;
    @Column(name = "open_on_start")
    private Integer openOnStartDate;
    @Column(name = "open_on_end")
    private Integer openOnEndPeriod;
    @Column(name = "close_contract_by_period")
    private Integer closeContractByPeriod;
    @Column(name = "open_contract_by_period")
    private Integer openContractByPeriod;
    @Column(name = "count_safe")
    private Integer countSafe;

    public Integer getDepartmentId() {
        return departmentId;
    }

    public void setDepartmentId(Integer departmentId) {
        this.departmentId = departmentId;
    }

    public String getDepartmentName() {
        return departmentName;
    }

    public void setDepartmentName(String departmentName) {
        this.departmentName = departmentName;
    }

    public Integer getOpenOnStartDate() {
        return openOnStartDate;
    }

    public void setOpenOnStartDate(Integer openOnStartDate) {
        this.openOnStartDate = openOnStartDate;
    }

    public Integer getOpenOnEndPeriod() {
        return openOnEndPeriod;
    }

    public void setOpenOnEndPeriod(Integer openOnEndPeriod) {
        this.openOnEndPeriod = openOnEndPeriod;
    }

    public Integer getCloseContractByPeriod() {
        return closeContractByPeriod;
    }

    public void setCloseContractByPeriod(Integer closeContractByPeriod) {
        this.closeContractByPeriod = closeContractByPeriod;
    }

    public Integer getOpenContractByPeriod() {
        return openContractByPeriod;
    }

    public void setOpenContractByPeriod(Integer openContractByPeriod) {
        this.openContractByPeriod = openContractByPeriod;
    }

    public Integer getCountSafe() {
        return countSafe;
    }

    public void setCountSafe(Integer countSafe) {
        this.countSafe = countSafe;
    }

}
