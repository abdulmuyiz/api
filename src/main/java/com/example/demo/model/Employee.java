package com.example.demo.model;

import com.fasterxml.jackson.annotation.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Timestamp;



@Entity
@Table(name = "employees")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
public class Employee implements Serializable {

    public enum EmpStatus{
        Active, Inactive
    }

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(name = "name")
    private String name;
    @Column(name = "age")
    private int age;
    @Column(name = "address")
    private String address;
    @Column(name = "contact_number")
    private String contact_number;
    @Column(name = "status", nullable = false)
    @Enumerated(EnumType.STRING)
    private EmpStatus status = EmpStatus.Active;
    @ManyToOne
    @JoinColumn(name = "department_id")
    @JsonProperty("department_id")
    @JsonIdentityReference(alwaysAsId = true)
    private Department department;
    @ManyToOne
    @JoinColumn(name= "office_id")
    @JsonProperty("office_id")
    @JsonIdentityReference(alwaysAsId = true)
    private Office office;
    @Column(name= "created_at")
    @CreationTimestamp
    private Timestamp created;
    @Column(name = "updated_at")
    @UpdateTimestamp
    private Timestamp updated;
}
