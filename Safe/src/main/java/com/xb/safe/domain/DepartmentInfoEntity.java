package com.xb.safe.domain;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Table(name = "department_card")
public class DepartmentInfoEntity implements Serializable {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @NotNull
    @Column(name = "dept_id")
    private Integer deptartmentId;
    @NotNull
    @Size(min = 1, max = 2147483647)
    @Column(name = "dept_name")
    private String departmentName;
    @NotNull
    @Size(min = 1, max = 2147483647)
    @Column(name = "address")
    private String address;
    @NotNull
    @Size(min = 1, max = 2147483647)
    @Column(name = "phone")
    private String phone;
    @NotNull
    @Size(min = 1, max = 2147483647)
    @Column(name = "name")
    private String name;
    @NotNull
    @Size(min = 1, max = 2147483647)
    @Column(name = "surname")
    private String surname;
    @NotNull
    @Size(min = 1, max = 2147483647)
    @Column(name = "patronymic")
    private String patronymic;
    @NotNull
    @Size(min = 1, max = 2147483647)
    @Column(name = "name_genitive")
    private String nameGenitive;
    @NotNull
    @Size(min = 1, max = 2147483647)
    @Column(name = "surname_genitive")
    private String surnameGenitive;
    @NotNull
    @Size(min = 1, max = 2147483647)
    @Column(name = "patronymic_genitive")
    private String patronymicGenitive;
    @NotNull
    @Size(min = 1, max = 2147483647)
    @Column(name = "trust_letter")
    private String trustLetter;
    @NotNull
    @Size(min = 1, max = 2147483647)
    @Column(name = "trust_letter_date")
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date trustLetterDate;
    @NotNull
    @Size(min = 1, max = 2147483647)
    @Column(name = "mfo")
    private String mfo;
    @NotNull
    @Size(min = 1, max = 2147483647)
    @Column(name = "inn")
    private String inn;
    @NotNull
    @Size(min = 1, max = 2147483647)
    @Column(name = "cash_account")
    private String cashAccount;
    @NotNull
    @Size(min = 1, max = 2147483647)
    @Column(name = "outpost_account")
    private String outpostAccount;
    @NotNull
    @Size(min = 1, max = 2147483647)
    @Column(name = "rent_account")
    private String rentaccount;
    @NotNull
    @Size(min = 1, max = 2147483647)
    @Column(name = "city")
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

   
}
