// MedicineService.java
package com.myapp.schoolhealth.service;

import com.myapp.schoolhealth.entity.Medicine;
import com.myapp.schoolhealth.payload.request.MedicineRequest;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface MedicineService {
    Medicine createMedicine(MedicineRequest medicineRequest); // Đảm bảo chữ ký này
    Optional<Medicine> getMedicineById(UUID id); // Đảm bảo chữ ký này
    List<Medicine> getAllMedicines(); // Đảm bảo chữ ký này
    Medicine updateMedicine(UUID id, MedicineRequest medicineRequest); // Đảm bảo chữ ký này
    List<Medicine> getLowStockMedicines(); // Đảm bảo chữ ký này
    List<Medicine> getExpiringMedicines(); // Đảm bảo chữ ký này
    void deleteMedicine(UUID id); // Đảm bảo chữ ký này
}