package com.xb.safe.dto;

public class FillingCellsHolder {

    private Integer departmentId;
    private String departmentName;
    private Integer openOnStartDate;
    private Integer openOnEndPeriod;
    private Integer closeContractByPeriod;
    private Integer openContractByPeriod;
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

    @Override
    public String toString() {
        return "FillingCellsHolder{" + "departmentId=" + departmentId + ", departmentName=" + departmentName + ", openOnStartDate=" + openOnStartDate + ", openOnEndPeriod=" + openOnEndPeriod + ", closeContractByPeriod=" + closeContractByPeriod + ", openContractByPeriod=" + openContractByPeriod + ", countSafe=" + countSafe + '}';
    }

}
