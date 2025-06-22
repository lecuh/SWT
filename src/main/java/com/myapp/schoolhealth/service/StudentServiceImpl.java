// StudentServiceImpl.java (Chỉnh sửa)
package com.myapp.schoolhealth.service;

import com.myapp.schoolhealth.entity.Student;
import com.myapp.schoolhealth.exception.ResourceNotFoundException;
import com.myapp.schoolhealth.payload.request.StudentRequest;
import com.myapp.schoolhealth.repository.StudentRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional; // Cần thiết cho Optional<Student>
import java.util.UUID; // Cần thiết cho UUID nếu dùng

@Service
public class StudentServiceImpl implements StudentService {

    private final StudentRepository studentRepository;

    public StudentServiceImpl(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    @Override // Đảm bảo @Override đúng
    public List<Student> getAllStudents() {
        return studentRepository.findAll();
    }

    @Override // Đảm bảo @Override đúng
    public Optional<Student> getStudentByStudentCode(String studentCode) {
        return studentRepository.findByStudentCode(studentCode);
    }

    @Override // Đảm bảo @Override đúng
    public Student createStudent(StudentRequest studentRequest) {
        Student student = new Student();
        student.setStudentCode(studentRequest.getStudentCode());
        student.setName(studentRequest.getName());
        student.setGender(studentRequest.getGender());
        student.setDateOfBirth(LocalDate.parse(studentRequest.getDateOfBirth()));
        student.setHeight(studentRequest.getHeight());
        student.setWeight(studentRequest.getWeight());
        student.setParentPhone(studentRequest.getParentPhone());
        student.setClassName(studentRequest.getClassName());
        return studentRepository.save(student);
    }

    @Override // Đảm bảo @Override đúng
    public Student updateStudent(String studentCode, StudentRequest studentRequest) {
        Student student = studentRepository.findByStudentCode(studentCode)
                .orElseThrow(() -> new ResourceNotFoundException("Student not found with code: " + studentCode));

        student.setName(studentRequest.getName());
        student.setGender(studentRequest.getGender());
        student.setDateOfBirth(LocalDate.parse(studentRequest.getDateOfBirth()));
        student.setHeight(studentRequest.getHeight());
        student.setWeight(studentRequest.getWeight());
        student.setParentPhone(studentRequest.getParentPhone());
        student.setClassName(studentRequest.getClassName());

        return studentRepository.save(student);
    }

    @Override // Đảm bảo @Override đúng
    @Transactional
    public void deleteStudent(String studentCode) {
        if (!studentRepository.findByStudentCode(studentCode).isPresent()) {
            throw new ResourceNotFoundException("Student not found with code: " + studentCode);
        }
        studentRepository.deleteByStudentCode(studentCode);
    }

    @Override // Đảm bảo @Override đúng
    public List<Student> getStudentsByClassName(String className) {
        return studentRepository.findByClassName(className);
    }
}