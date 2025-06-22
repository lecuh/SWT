// MedicalHistoryService.java
package com.myapp.schoolhealth.service;

import com.myapp.schoolhealth.entity.MedicalHistory;
import com.myapp.schoolhealth.payload.request.MedicalHistoryRequest;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface MedicalHistoryService {
    MedicalHistory createMedicalHistory(MedicalHistoryRequest medicalHistoryRequest); // Đảm bảo chữ ký này
    Optional<MedicalHistory> getMedicalHistoryById(UUID id);
    List<MedicalHistory> getMedicalHistoryByStudentCode(String studentCode); // Đảm bảo chữ ký này
}