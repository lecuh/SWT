// PrescriptionService.java
package com.myapp.schoolhealth.service;

import com.myapp.schoolhealth.entity.Prescription;
import com.myapp.schoolhealth.payload.request.PrescriptionRequest;
import com.myapp.schoolhealth.payload.dto.PrescriptionDTO;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface PrescriptionService {
    Prescription createPrescription(PrescriptionRequest prescriptionRequest);
    Optional<Prescription> getPrescriptionById(UUID id);
    List<PrescriptionDTO> getPrescriptionsByStudentCode(String studentCode);
    List<PrescriptionDTO> getAllPrescriptions();
    Prescription updateConfirmedDose(UUID id, boolean confirmed);
    void deletePrescription(UUID id);
}