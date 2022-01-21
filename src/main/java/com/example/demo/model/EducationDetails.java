package com.example.demo.model;

import javax.persistence.*;
import java.sql.Timestamp;



@Entity
@Table(name = "education_details")
public class EducationDetails {
    public enum SourceType{
        CGPA, Percentage
    }
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @OneToOne
    @JoinColumn(name = "employee_id")
    private Employee employee;
    @OneToOne
    @JoinColumn(name = "qualification_type_id")
    private QualificationTypes qualificationTypes;
    @Column(name = "score_type")
    private SourceType sourceType;
    @Column(name = "score")
    private double score;
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

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    public QualificationTypes getQualificationTypes() {
        return qualificationTypes;
    }

    public void setQualificationTypes(QualificationTypes qualificationTypes) {
        this.qualificationTypes = qualificationTypes;
    }

    public SourceType getSourceType() {
        return sourceType;
    }

    public void setSourceType(SourceType sourceType) {
        this.sourceType = sourceType;
    }

    public double getScore() {
        return score;
    }

    public void setScore(double score) {
        this.score = score;
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
