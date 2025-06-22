// src/main/java/com/myapp/schoolhealth/repository/PrescriptionRepository.java
package com.myapp.schoolhealth.repository;

import com.myapp.schoolhealth.entity.Prescription;
import com.myapp.schoolhealth.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface PrescriptionRepository extends JpaRepository<Prescription, UUID> {
    List<Prescription> findByStudent(Student student);
}