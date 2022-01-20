package com.example.demo.model;

import javax.persistence.*;
import java.sql.Date;
import java.sql.Timestamp;

enum SalaryStatus{
    Active, Inactive;
}

@Entity
@Table(name = "salaries")
public class Salary {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;
    @Column(name="account")
    private double account;
    @Column(name= "begin_date")
    private Date begin_date;
    @Column(name="status")
    private SalaryStatus status;
    @OneToOne
    @JoinColumn(name="employee_id")
    private Employee employee;
    @Column(name= "created_at")
    private Timestamp created;
    @Column(name = "updated_at")
    private Timestamp updated;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public double getAccount() {
        return account;
    }

    public void setAccount(double account) {
        this.account = account;
    }

    public Date getBegin_date() {
        return begin_date;
    }

    public void setBegin_date(Date begin_date) {
        this.begin_date = begin_date;
    }

    public SalaryStatus getStatus() {
        return status;
    }

    public void setStatus(SalaryStatus status) {
        this.status = status;
    }

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
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
