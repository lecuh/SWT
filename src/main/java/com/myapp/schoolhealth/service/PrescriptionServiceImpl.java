// PrescriptionServiceImpl.java (Chỉnh sửa)
package com.myapp.schoolhealth.service;

import com.myapp.schoolhealth.entity.Medicine;
import com.myapp.schoolhealth.entity.Prescription;
import com.myapp.schoolhealth.entity.Student;
import com.myapp.schoolhealth.exception.ResourceNotFoundException;
import com.myapp.schoolhealth.payload.request.PrescriptionRequest;
import com.myapp.schoolhealth.repository.MedicineRepository;
import com.myapp.schoolhealth.repository.PrescriptionRepository;
import com.myapp.schoolhealth.repository.StudentRepository;

import org.hibernate.Hibernate;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional; // Cần thiết cho Optional<Prescription> và Optional<Student>
import java.util.UUID;
import java.util.stream.Collectors;

import com.myapp.schoolhealth.payload.dto.PrescriptionDTO;
import com.myapp.schoolhealth.payload.dto.PrescriptionMapper;

@Service
public class PrescriptionServiceImpl implements PrescriptionService {

    private final PrescriptionRepository prescriptionRepository;
    private final StudentRepository studentRepository;
    private final MedicineRepository medicineRepository;

    public PrescriptionServiceImpl(PrescriptionRepository prescriptionRepository, StudentRepository studentRepository,
            MedicineRepository medicineRepository) {
        this.prescriptionRepository = prescriptionRepository;
        this.studentRepository = studentRepository;
        this.medicineRepository = medicineRepository;
    }

    @Override // Đảm bảo @Override đúng
    public Prescription createPrescription(PrescriptionRequest prescriptionRequest) {
        Student student = studentRepository.findByStudentCode(prescriptionRequest.getStudentId())
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Student not found with code: " + prescriptionRequest.getStudentId()));

        Medicine medicine = medicineRepository.findById(prescriptionRequest.getMedicineId())
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Medicine not found with ID: " + prescriptionRequest.getMedicineId()));

        Prescription prescription = new Prescription();
        prescription.setStudent(student);
        prescription.setMedicine(medicine);
        prescription.setDosage(prescriptionRequest.getDosage());
        prescription.setUsageInstructions(prescriptionRequest.getUsageInstructions());
        prescription.setConsent(prescriptionRequest.getConsent());
        prescription.setDatePrescribed(LocalDate.parse(prescriptionRequest.getDatePrescribed()));
        prescription.setConfirmedDose(false);

        return prescriptionRepository.save(prescription);
    }

    @Override // Đảm bảo @Override đúng
    public Optional<Prescription> getPrescriptionById(UUID id) {
        return prescriptionRepository.findById(id);
    }

    @Override
    public List<PrescriptionDTO> getPrescriptionsByStudentCode(String studentCode) {
        Student student = studentRepository.findByStudentCode(studentCode)
                .orElseThrow(() -> new ResourceNotFoundException("Student not found with code: " + studentCode));

        List<Prescription> prescriptions = prescriptionRepository.findByStudent(student);

        // ✅ Phải khởi tạo các quan hệ LAZY để không bị null khi mapping
        prescriptions.forEach(p -> {
            Hibernate.initialize(p.getStudent());
            Hibernate.initialize(p.getMedicine());
        });

        return prescriptions.stream()
                .map(PrescriptionMapper::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    public List<PrescriptionDTO> getAllPrescriptions() {
        List<Prescription> prescriptions = prescriptionRepository.findAll();

        prescriptions.forEach(p -> {
            Hibernate.initialize(p.getStudent());
            Hibernate.initialize(p.getMedicine());
        });

        return prescriptions.stream()
                .map(PrescriptionMapper::toDTO)
                .collect(Collectors.toList());
    }

    @Override // Đảm bảo @Override đúng
    public Prescription updateConfirmedDose(UUID id, boolean confirmed) {
        Prescription prescription = prescriptionRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Prescription not found with id: " + id));
        prescription.setConfirmedDose(confirmed);
        return prescriptionRepository.save(prescription);
    }

    @Override
    public void deletePrescription(UUID id) {
        if (!prescriptionRepository.existsById(id)) {
            throw new ResourceNotFoundException("Prescription not found with id: " + id);
        }
        prescriptionRepository.deleteById(id);
    }

}