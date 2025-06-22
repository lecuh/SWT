// src/main/java/com/myapp/schoolhealth/repository/StudentRepository.java
package com.myapp.schoolhealth.repository;

import com.myapp.schoolhealth.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List; // THÊM IMPORT này
import java.util.Optional;
import java.util.UUID;

@Repository
public interface StudentRepository extends JpaRepository<Student, UUID> {
    Optional<Student> findByStudentCode(String studentCode);

    @Transactional
    void deleteByStudentCode(String studentCode);

    List<Student> findByClassName(String className); // THÊM MỚI
}