// src/main/java/com/myapp/schoolhealth/repository/MedicalHistoryRepository.java
package com.myapp.schoolhealth.repository;

import com.myapp.schoolhealth.entity.MedicalHistory;
import com.myapp.schoolhealth.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface MedicalHistoryRepository extends JpaRepository<MedicalHistory, UUID> {
    List<MedicalHistory> findByStudent(Student student);
}