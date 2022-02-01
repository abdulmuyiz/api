package com.example.demo.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.data.redis.core.RedisHash;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Timestamp;



@Entity
@Table(name = "departments")
public class Department implements Serializable {

    public Department() {
    }

    public Department(long id, String name, String type, DepStatus status, Office office, Timestamp created, Timestamp updated) {
        this.id = id;
        this.name = name;
        this.type = type;
        this.status = status;
        this.office = office;
        this.created = created;
        this.updated = updated;
    }

    public enum DepStatus{
        Active, Inactive
    }
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(name = "name")
    private String name;
    @Column(name = "type")
    private String type;
    @Column(name = "status")
    @Enumerated(EnumType.STRING)
    private DepStatus status;
    @ManyToOne
    @JoinColumn(name = "office_id")
    @JsonProperty("office_id")
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

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public DepStatus getStatus() {
        return status;
    }

    public void setStatus(DepStatus status) {
        this.status = status;
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
