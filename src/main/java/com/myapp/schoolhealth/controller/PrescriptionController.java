// src/main/java/com/myapp/schoolhealth/controller/PrescriptionController.java
package com.myapp.schoolhealth.controller;

import com.myapp.schoolhealth.entity.Prescription;
import com.myapp.schoolhealth.payload.request.PrescriptionRequest;
import com.myapp.schoolhealth.service.PrescriptionService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

import com.myapp.schoolhealth.payload.dto.PrescriptionDTO;

@RestController
@RequestMapping("/api/prescriptions")
public class PrescriptionController {

    private final PrescriptionService prescriptionService;

    public PrescriptionController(PrescriptionService prescriptionService) {
        this.prescriptionService = prescriptionService;
    }

    @PostMapping
    public ResponseEntity<Prescription> createPrescription(@RequestBody PrescriptionRequest prescriptionRequest) {
        Prescription createdPrescription = prescriptionService.createPrescription(prescriptionRequest);
        return new ResponseEntity<>(createdPrescription, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Prescription> getPrescriptionById(@PathVariable UUID id) {
        return prescriptionService.getPrescriptionById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/student/{studentCode}")
    public ResponseEntity<List<PrescriptionDTO>> getPrescriptionsByStudentCode(@PathVariable String studentCode) {
        List<PrescriptionDTO> prescriptions = prescriptionService.getPrescriptionsByStudentCode(studentCode);
        return ResponseEntity.ok(prescriptions);
    }

    @GetMapping
    public ResponseEntity<List<PrescriptionDTO>> getAllPrescriptions() {
        List<PrescriptionDTO> prescriptions = prescriptionService.getAllPrescriptions();
        return ResponseEntity.ok(prescriptions);
    }

    @PutMapping("/{id}/confirm")
    public ResponseEntity<Prescription> updateConfirmedDose(@PathVariable UUID id, @RequestParam boolean confirmed) {
        Prescription updatedPrescription = prescriptionService.updateConfirmedDose(id, confirmed);
        return ResponseEntity.ok(updatedPrescription);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePrescription(@PathVariable UUID id) {
        prescriptionService.deletePrescription(id);
        return ResponseEntity.noContent().build(); // 204
    }
}