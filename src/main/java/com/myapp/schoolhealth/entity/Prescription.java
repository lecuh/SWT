// src/main/java/com/myapp/schoolhealth/entity/Prescription.java
package com.myapp.schoolhealth.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.time.LocalDate;
import java.util.UUID;

@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@Entity
@Table(name = "prescriptions")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Prescription {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "student_id", nullable = false)
    private Student student;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "medicine_id", nullable = false)
    private Medicine medicine;

    @Column
    private String dosage;

    @Column
    private String usageInstructions;

    @Column
    private Boolean consent;

    @Column
    private LocalDate datePrescribed;

    @Column
    private boolean confirmedDose; // ĐÃ THÊM: Trường để lưu trạng thái xác nhận đã uống hàng ngày
}