// src/main/java/com/myapp/schoolhealth/SchoolhealthApplication.java
package com.myapp.schoolhealth;

import com.myapp.schoolhealth.entity.Nurse;
import com.myapp.schoolhealth.repository.NurseRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder; // Đảm bảo import này

@SpringBootApplication
public class SchoolhealthApplication {

    public static void main(String[] args) {
        SpringApplication.run(SchoolhealthApplication.class, args);
    }

    @Bean
    public CommandLineRunner demoData(NurseRepository nurseRepository, PasswordEncoder passwordEncoder) {
        return args -> {
            // Kiểm tra xem user "phuc05" đã tồn tại chưa để tránh trùng lặp khi khởi động lại
            if (nurseRepository.findByUsername("phuc05").isEmpty()) {
                Nurse nurse = new Nurse();
                nurse.setUsername("phuc05");
                nurse.setPassword(passwordEncoder.encode("1")); // MÃ HÓA MẬT KHẨU '1'
                nurse.setRoles("ROLE_NURSE"); // Đặt vai trò, ví dụ: "ROLE_NURSE" hoặc "ROLE_ADMIN,ROLE_NURSE"
                nurseRepository.save(nurse);
                System.out.println("Nurse 'phuc05' created with encoded password.");
            } else {
                System.out.println("Nurse 'phuc05' already exists.");
            }
        };
    }
}