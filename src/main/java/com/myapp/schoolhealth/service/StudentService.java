// StudentService.java
package com.myapp.schoolhealth.service;

import com.myapp.schoolhealth.entity.Student;
import com.myapp.schoolhealth.payload.request.StudentRequest;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface StudentService {
    List<Student> getAllStudents(); // Đảm bảo chữ ký này

    Optional<Student> getStudentByStudentCode(String studentCode); // Đảm bảo chữ ký này

    Student createStudent(StudentRequest studentRequest); // Đảm bảo chữ ký này

    Student updateStudent(String studentCode, StudentRequest studentRequest); // Đảm bảo chữ ký này

    void deleteStudent(String studentCode); // Đảm bảo chữ ký này

    List<Student> getStudentsByClassName(String className); // Đảm bảo chữ ký này
}