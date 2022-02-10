package com.example.demo.model;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIdentityReference;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
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
@Table(name = "education_details")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
public class EducationDetails implements Serializable {
    public enum SourceType{
        CGPA, Percentage
    }
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @ManyToOne
    @JoinColumn(name = "employee_id")
    @JsonProperty("employee_id")
    @JsonIdentityReference(alwaysAsId = true)
    private Employee employee;
    @OneToOne
    @JoinColumn(name = "qualification_type_id")
    @JsonProperty("qualification_type_id")
    @JsonIdentityReference(alwaysAsId = true)
    private QualificationTypes qualificationTypes;
    @Column(name = "score_type", nullable = false)
    @JsonProperty("score_type")
    @Enumerated(EnumType.STRING)
    private SourceType sourceType;
    @Column(name = "score")
    private double score;
    @Column(name= "created_at")
    @CreationTimestamp
    private Timestamp created;
    @Column(name = "updated_at")
    @UpdateTimestamp
    private Timestamp updated;
}
