package com.example.demo.model;


import com.fasterxml.jackson.annotation.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Timestamp;

@Entity
@Table(name = "departments")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
public class Department implements Serializable {
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
    private DepStatus status = DepStatus.Active;
    @ManyToOne
    @JoinColumn(name = "office_id")
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
