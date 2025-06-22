// MedicineRepository.java
package com.myapp.schoolhealth.repository;

import com.myapp.schoolhealth.entity.Medicine;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate; // Thêm import này
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface MedicineRepository extends JpaRepository<Medicine, UUID> {
    Optional<Medicine> findByName(String name);
    List<Medicine> findByQuantityLessThan(int quantity);

    // THÊM MỚI: Phương thức để tìm thuốc hết hạn trong một khoảng thời gian
    List<Medicine> findByExpiryDateBetween(LocalDate startDate, LocalDate endDate);
    // Hoặc đơn giản hơn cho "sắp hết hạn" nếu bạn chỉ cần ngày trước một ngưỡng
    // List<Medicine> findByExpiryDateBefore(LocalDate date);
}