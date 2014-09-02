package com.xb.safe.dto;

import java.util.Date;

public class Safe {

    private Integer id;
    private String code;
    private String status;
    private Department department;
    private Model model;
    private String date;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Department getDepartment() {
        return department;
    }

    public void setDepartment(Department department) {
        this.department = department;
    }

    public Model getModel() {
        return model;
    }

    public void setModel(Model model) {
        this.model = model;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return "Safe{" + "id=" + id + ", code=" + code + ", status=" + status + ", department=" + department + ", model=" + model + ", date=" + date + '}';
    }


}
