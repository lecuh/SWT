// src/main/java/com/myapp/schoolhealth/controller/StudentController.java
package com.myapp.schoolhealth.controller;

import com.myapp.schoolhealth.entity.Student;
import com.myapp.schoolhealth.payload.request.StudentRequest;
import com.myapp.schoolhealth.service.StudentService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/students")
public class StudentController {

    private final StudentService studentService;

    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @GetMapping
    public ResponseEntity<List<Student>> getAllStudents() {
        List<Student> students = studentService.getAllStudents();
        return ResponseEntity.ok(students);
    }

    @GetMapping("/{studentCode}")
    public ResponseEntity<Student> getStudentByStudentCode(@PathVariable String studentCode) {
        return studentService.getStudentByStudentCode(studentCode)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/by-class/{className}")
    public ResponseEntity<List<Student>> getStudentsByClassName(@PathVariable String className) {
        List<Student> students = studentService.getStudentsByClassName(className);
        return ResponseEntity.ok(students);
    }

    @PostMapping
    public ResponseEntity<Student> createStudent(@RequestBody StudentRequest studentRequest) {
        Student createdStudent = studentService.createStudent(studentRequest);
        return new ResponseEntity<>(createdStudent, HttpStatus.CREATED);
    }

    @PutMapping("/{studentCode}")
    public ResponseEntity<Student> updateStudent(@PathVariable String studentCode, @RequestBody StudentRequest studentRequest) {
        Student updatedStudent = studentService.updateStudent(studentCode, studentRequest);
        return ResponseEntity.ok(updatedStudent);
    }

    @DeleteMapping("/{studentCode}")
    public ResponseEntity<Void> deleteStudent(@PathVariable String studentCode) {
        studentService.deleteStudent(studentCode);
        return ResponseEntity.noContent().build();
    }
}