// src/main/java/com/myapp/schoolhealth/payload/request/PrescriptionRequest.java
package com.myapp.schoolhealth.payload.request;

import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;

import java.util.UUID;

import lombok.AllArgsConstructor;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PrescriptionRequest {
    private String studentId;
    private UUID medicineId;
    private String dosage;
    private String usageInstructions;
    private Boolean consent;
    private String datePrescribed;
}