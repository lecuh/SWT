// src/main/java/com/myapp/schoolhealth/repository/NurseRepository.java
package com.myapp.schoolhealth.repository;

import com.myapp.schoolhealth.entity.Nurse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface NurseRepository extends JpaRepository<Nurse, UUID> {
    Optional<Nurse> findByUsername(String username);
}