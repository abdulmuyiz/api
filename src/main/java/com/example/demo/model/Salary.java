package com.example.demo.model;

import com.fasterxml.jackson.annotation.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Date;
import java.sql.Timestamp;



@Entity
@Table(name = "salaries")
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
public class Salary implements Serializable {
    public enum SalaryStatus{
        Active, Inactive
    }
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;
    @Column(name="amount")
    private double amount;
    @Column(name= "begin_date")
    private Date begin_date;
    @Column(name="status", nullable = false)
    @Enumerated(EnumType.STRING)
    private SalaryStatus status = SalaryStatus.Active;
    @ManyToOne
    @JoinColumn(name="employee_id")
    @JsonProperty("employee_id")
    @JsonIdentityReference(alwaysAsId = true)
    private Employee employee;
    @Column(name= "created_at")
    @CreationTimestamp
    private Timestamp created;
    @Column(name = "updated_at")
    @UpdateTimestamp
    private Timestamp updated;
}
