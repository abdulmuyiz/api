package com.example.demo.model;

import javax.persistence.*;
import java.sql.Timestamp;



@Entity
@Table(name = "employees")
public class Employee {

    public enum EmpStatus{
        Active, Inactive;
    }

    @Id
    @Column(name = "id")
    private long id;
    @Column(name = "name")
    private String name;
    @Column(name = "age")
    private int age;
    @Column(name = "address")
    private String address;
    @Column(name = "contact_number")
    private String contact_number;
    @Column(name = "status")
    private EmpStatus status;
    @ManyToOne
    @JoinColumn(name = "department_id")
    private Department department;
    @ManyToOne
    @JoinColumn(name= "office_id")
    private Office office;
    @Column(name= "created_at")
    private Timestamp created;
    @Column(name = "updated_at")
    private Timestamp updated;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getContact_number() {
        return contact_number;
    }

    public void setContact_number(String contact_number) {
        this.contact_number = contact_number;
    }

    public EmpStatus getStatus() {
        return status;
    }

    public void setStatus(EmpStatus status) {
        this.status = status;
    }

    public Department getDepartment() {
        return department;
    }

    public void setDepartment(Department department) {
        this.department = department;
    }

    public Office getOffice() {
        return office;
    }

    public void setOffice(Office office) {
        this.office = office;
    }

    public Timestamp getCreated() {
        return created;
    }

    public void setCreated(Timestamp created) {
        this.created = created;
    }

    public Timestamp getUpdated() {
        return updated;
    }

    public void setUpdated(Timestamp updated) {
        this.updated = updated;
    }
}
