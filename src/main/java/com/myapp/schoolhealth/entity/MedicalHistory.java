// src/main/java/com/myapp/schoolhealth/entity/MedicalHistory.java
package com.myapp.schoolhealth.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

import java.util.UUID;

@Entity
@Table(name = "medical_histories")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class MedicalHistory {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "student_id", nullable = false)
    private Student student;

    @Column
    private String medicalCondition;

    @Column
    private String allergy;

    @Column
    private String notes;
}