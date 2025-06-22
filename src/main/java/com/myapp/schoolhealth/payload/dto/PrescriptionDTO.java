package com.myapp.schoolhealth.payload.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
public class PrescriptionDTO {
    private UUID id; // ✅ phải có để FE gọi xoá đúng
    private String studentCode;
    private String studentName;
    private String medicineName;
    private String dosage;
    private String usageInstructions;
    private Boolean consent;
    private LocalDate datePrescribed;
    private boolean confirmedDose;
}
