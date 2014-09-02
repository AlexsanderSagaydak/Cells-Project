package com.xb.safe.domain;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "safe")
public class SafeEntity implements Serializable {
    @Id
    @Column(name = "safe_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(name = "status")
    private String status;
    @ManyToOne
    @JoinColumn(name = "dept_id")
    private DepartmentEntity department;
    @ManyToOne
    @JoinColumn(name = "model_id")
    private ModelEntity model;
    @Column(name = "code")
    private Integer code;

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }
    
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
    
    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public DepartmentEntity getDepartment() {
        return department;
    }

    public void setDepartment(DepartmentEntity department) {
        this.department = department;
    }

    public ModelEntity getModel() {
        return model;
    }

    public void setModel(ModelEntity model) {
        this.model = model;
    }
}
