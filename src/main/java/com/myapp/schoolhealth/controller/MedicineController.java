// src/main/java/com/myapp/schoolhealth/controller/MedicineController.java
package com.myapp.schoolhealth.controller;

import com.myapp.schoolhealth.entity.Medicine;
import com.myapp.schoolhealth.payload.request.MedicineRequest;
import com.myapp.schoolhealth.service.MedicineService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/medicines")
public class MedicineController {

    private final MedicineService medicineService;

    public MedicineController(MedicineService medicineService) {
        this.medicineService = medicineService;
    }

    @PostMapping
    public ResponseEntity<Medicine> createMedicine(@RequestBody MedicineRequest medicineRequest) {
        Medicine createdMedicine = medicineService.createMedicine(medicineRequest);
        return new ResponseEntity<>(createdMedicine, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Medicine> getMedicineById(@PathVariable UUID id) {
        return medicineService.getMedicineById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping
    public ResponseEntity<List<Medicine>> getAllMedicines() {
        List<Medicine> medicines = medicineService.getAllMedicines();
        return ResponseEntity.ok(medicines);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Medicine> updateMedicine(@PathVariable UUID id, @RequestBody MedicineRequest medicineRequest) {
        Medicine updatedMedicine = medicineService.updateMedicine(id, medicineRequest);
        return ResponseEntity.ok(updatedMedicine);
    }

    @GetMapping("/low-stock")
    public ResponseEntity<List<Medicine>> getLowStockMedicines() {
        List<Medicine> lowStockMedicines = medicineService.getLowStockMedicines();
        return ResponseEntity.ok(lowStockMedicines);
    }

    @GetMapping("/expiring")
    public ResponseEntity<List<Medicine>> getExpiringMedicines() {
        List<Medicine> expiringMedicines = medicineService.getExpiringMedicines();
        return ResponseEntity.ok(expiringMedicines);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteMedicine(@PathVariable UUID id) {
        medicineService.deleteMedicine(id);
        return ResponseEntity.noContent().build();
    }
}