// src/main/java/com/myapp/schoolhealth/payload/request/MedicalHistoryRequest.java
package com.myapp.schoolhealth.payload.request;

import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class MedicalHistoryRequest {
    private String studentCode;
    private String medicalCondition;
    private String allergy;
    private String notes;
}