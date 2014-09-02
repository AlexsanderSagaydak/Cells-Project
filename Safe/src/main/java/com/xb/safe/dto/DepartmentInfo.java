package com.xb.safe.dto;

import java.util.Date;

public class DepartmentInfo {

    private Integer id;
    private Integer deptartmentId;
    private String departmentName;
    private String address;
    private String phone;
    private String name;
    private String surname;
    private String patronymic;
    private String nameGenitive;
    private String surnameGenitive;
    private String patronymicGenitive;
    private String trustLetter;
    private Date trustLetterDate;
    private String mfo;
    private String inn;
    private String cashAccount;
    private String outpostAccount;
    private String rentaccount;
    private String city;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getDeptartmentId() {
        return deptartmentId;
    }

    public void setDeptartmentId(Integer deptartmentId) {
        this.deptartmentId = deptartmentId;
    }

    public String getDepartmentName() {
        return departmentName;
    }

    public void setDepartmentName(String departmentName) {
        this.departmentName = departmentName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getPatronymic() {
        return patronymic;
    }

    public void setPatronymic(String patronymic) {
        this.patronymic = patronymic;
    }

    public String getNameGenitive() {
        return nameGenitive;
    }

    public void setNameGenitive(String nameGenitive) {
        this.nameGenitive = nameGenitive;
    }

    public String getSurnameGenitive() {
        return surnameGenitive;
    }

    public void setSurnameGenitive(String surnameGenitive) {
        this.surnameGenitive = surnameGenitive;
    }

    public String getPatronymicGenitive() {
        return patronymicGenitive;
    }

    public void setPatronymicGenitive(String patronymicGenitive) {
        this.patronymicGenitive = patronymicGenitive;
    }

    public String getTrustLetter() {
        return trustLetter;
    }

    public void setTrustLetter(String trustLetter) {
        this.trustLetter = trustLetter;
    }

    public Date getTrustLetterDate() {
        return trustLetterDate;
    }

    public void setTrustLetterDate(Date trustLetterDate) {
        this.trustLetterDate = trustLetterDate;
    }

    public String getMfo() {
        return mfo;
    }

    public void setMfo(String mfo) {
        this.mfo = mfo;
    }

    public String getInn() {
        return inn;
    }

    public void setInn(String inn) {
        this.inn = inn;
    }

    public String getCashAccount() {
        return cashAccount;
    }

    public void setCashAccount(String cashAccount) {
        this.cashAccount = cashAccount;
    }

    public String getOutpostAccount() {
        return outpostAccount;
    }

    public void setOutpostAccount(String outpostAccount) {
        this.outpostAccount = outpostAccount;
    }

    public String getRentaccount() {
        return rentaccount;
    }

    public void setRentaccount(String rentaccount) {
        this.rentaccount = rentaccount;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    @Override
    public String toString() {
        return "DepartmentInfo{" + "id=" + id + ", deptartmentId=" + deptartmentId + ", departmentName=" + departmentName + ", address=" + address + ", phone=" + phone + ", name=" + name + ", surname=" + surname + ", patronymic=" + patronymic + ", nameGenitive=" + nameGenitive + ", surnameGenitive=" + surnameGenitive + ", patronymicGenitive=" + patronymicGenitive + ", trustLetter=" + trustLetter + ", trustLetterDate=" + trustLetterDate + ", mfo=" + mfo + ", inn=" + inn + ", cashAccount=" + cashAccount + ", outpostAccount=" + outpostAccount + ", rentaccount=" + rentaccount + ", city=" + city + '}';
    }

}
