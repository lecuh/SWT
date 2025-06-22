// MedicalHistoryServiceImpl.java (Chỉnh sửa)
package com.myapp.schoolhealth.service;

import com.myapp.schoolhealth.entity.MedicalHistory;
import com.myapp.schoolhealth.entity.Student;
import com.myapp.schoolhealth.exception.ResourceNotFoundException;
import com.myapp.schoolhealth.payload.request.MedicalHistoryRequest;
import com.myapp.schoolhealth.repository.MedicalHistoryRepository;
import com.myapp.schoolhealth.repository.StudentRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional; // Import này đang được dùng bởi Optional<MedicalHistory> và Optional<Student>
import java.util.UUID;

@Service
public class MedicalHistoryServiceImpl implements MedicalHistoryService {

    private final MedicalHistoryRepository medicalHistoryRepository;
    private final StudentRepository studentRepository;

    public MedicalHistoryServiceImpl(MedicalHistoryRepository medicalHistoryRepository, StudentRepository studentRepository) {
        this.medicalHistoryRepository = medicalHistoryRepository;
        this.studentRepository = studentRepository;
    }

    @Override // Đảm bảo @Override đúng
    public MedicalHistory createMedicalHistory(MedicalHistoryRequest medicalHistoryRequest) {
        Student student = studentRepository.findByStudentCode(medicalHistoryRequest.getStudentCode())
                .orElseThrow(() -> new ResourceNotFoundException("Student not found with code: " + medicalHistoryRequest.getStudentCode()));

        MedicalHistory medicalHistory = new MedicalHistory();
        medicalHistory.setStudent(student);
        medicalHistory.setMedicalCondition(medicalHistoryRequest.getMedicalCondition());
        medicalHistory.setAllergy(medicalHistoryRequest.getAllergy());
        medicalHistory.setNotes(medicalHistoryRequest.getNotes());

        return medicalHistoryRepository.save(medicalHistory);
    }

    @Override // Đảm bảo @Override đúng
    public Optional<MedicalHistory> getMedicalHistoryById(UUID id) {
        return medicalHistoryRepository.findById(id);
    }

    @Override // Đảm bảo @Override đúng
    public List<MedicalHistory> getMedicalHistoryByStudentCode(String studentCode) {
        Student student = studentRepository.findByStudentCode(studentCode)
                .orElseThrow(() -> new ResourceNotFoundException("Student not found with code: " + studentCode));
        return medicalHistoryRepository.findByStudent(student);
    }
}