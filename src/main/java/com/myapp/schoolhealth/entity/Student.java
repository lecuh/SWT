// src/main/java/com/myapp/schoolhealth/entity/Student.java
package com.myapp.schoolhealth.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.time.LocalDate;
import java.util.UUID;


@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@Entity
@Table(name = "students")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Student {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(unique = true, nullable = false)
    private String studentCode;

    @Column(nullable = false)
    private String name;

    @Column
    private String gender;

    @Column
    private LocalDate dateOfBirth;

    @Column
    private Double height;

    @Column
    private Double weight;

    @Column
    private String parentPhone;

    @Column
    private String className; // ĐÃ THÊM: trường className để phù hợp với frontend
}