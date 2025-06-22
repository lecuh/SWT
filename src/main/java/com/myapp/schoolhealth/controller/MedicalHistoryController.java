// src/main/java/com/myapp/schoolhealth/controller/MedicalHistoryController.java
package com.myapp.schoolhealth.controller;

import com.myapp.schoolhealth.entity.MedicalHistory;
import com.myapp.schoolhealth.payload.request.MedicalHistoryRequest;
import com.myapp.schoolhealth.service.MedicalHistoryService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/medical-history")
public class MedicalHistoryController {

    private final MedicalHistoryService medicalHistoryService;

    public MedicalHistoryController(MedicalHistoryService medicalHistoryService) {
        this.medicalHistoryService = medicalHistoryService;
    }

    @PostMapping
    public ResponseEntity<MedicalHistory> createMedicalHistory(@RequestBody MedicalHistoryRequest medicalHistoryRequest) {
        MedicalHistory createdMedicalHistory = medicalHistoryService.createMedicalHistory(medicalHistoryRequest);
        return new ResponseEntity<>(createdMedicalHistory, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<MedicalHistory> getMedicalHistoryById(@PathVariable UUID id) {
        return medicalHistoryService.getMedicalHistoryById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/student/{studentCode}")
    public ResponseEntity<List<MedicalHistory>> getMedicalHistoryByStudentCode(@PathVariable String studentCode) {
        List<MedicalHistory> medicalHistories = medicalHistoryService.getMedicalHistoryByStudentCode(studentCode);
        return ResponseEntity.ok(medicalHistories);
    }
}