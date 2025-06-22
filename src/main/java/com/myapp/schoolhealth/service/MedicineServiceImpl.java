// MedicineServiceImpl.java (Chỉnh sửa)
package com.myapp.schoolhealth.service;

import com.myapp.schoolhealth.entity.Medicine;
import com.myapp.schoolhealth.exception.ResourceNotFoundException;
import com.myapp.schoolhealth.payload.request.MedicineRequest;
import com.myapp.schoolhealth.repository.MedicineRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional; // Cần thiết cho Optional<Medicine>
import java.util.UUID;

@Service
public class MedicineServiceImpl implements MedicineService {

    private final MedicineRepository medicineRepository;

    public MedicineServiceImpl(MedicineRepository medicineRepository) {
        this.medicineRepository = medicineRepository;
    }

    @Override // Đảm bảo @Override đúng
    public Medicine createMedicine(MedicineRequest medicineRequest) {
        Medicine medicine = new Medicine();
        medicine.setName(medicineRequest.getName());
        medicine.setDescription(medicineRequest.getDescription());
        medicine.setQuantity(medicineRequest.getQuantity());
        medicine.setExpiryDate(LocalDate.parse(medicineRequest.getExpiryDate()));
        return medicineRepository.save(medicine);
    }

    @Override // Đảm bảo @Override đúng
    public Optional<Medicine> getMedicineById(UUID id) {
        return medicineRepository.findById(id);
    }

    @Override // Đảm bảo @Override đúng
    public List<Medicine> getAllMedicines() {
        return medicineRepository.findAll();
    }

    @Override // Đảm bảo @Override đúng
    public Medicine updateMedicine(UUID id, MedicineRequest medicineRequest) {
        Medicine medicine = medicineRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Medicine not found with id: " + id));

        medicine.setName(medicineRequest.getName());
        medicine.setDescription(medicineRequest.getDescription());
        medicine.setQuantity(medicineRequest.getQuantity());
        medicine.setExpiryDate(LocalDate.parse(medicineRequest.getExpiryDate()));

        return medicineRepository.save(medicine);
    }

    @Override // Đảm bảo @Override đúng
    public List<Medicine> getLowStockMedicines() {
        final int LOW_STOCK_THRESHOLD = 10;
        return medicineRepository.findByQuantityLessThan(LOW_STOCK_THRESHOLD);
    }

    @Override // Đảm bảo @Override đúng
    public List<Medicine> getExpiringMedicines() {
        LocalDate today = LocalDate.now();
        LocalDate expiryThreshold = today.plusMonths(3);

        return medicineRepository.findByExpiryDateBetween(today, expiryThreshold); // Sử dụng findByExpiryDateBetween
    }

    @Override // Đảm bảo @Override đúng
    public void deleteMedicine(UUID id) {
        if (!medicineRepository.existsById(id)) {
            throw new ResourceNotFoundException("Medicine not found with id: " + id);
        }
        medicineRepository.deleteById(id);
    }
}