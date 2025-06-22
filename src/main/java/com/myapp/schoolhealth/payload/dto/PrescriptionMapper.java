package com.myapp.schoolhealth.payload.dto;

import com.myapp.schoolhealth.entity.Prescription;
import com.myapp.schoolhealth.entity.Medicine;
import com.myapp.schoolhealth.entity.Student;

public class PrescriptionMapper {

    public static PrescriptionDTO toDTO(Prescription p) {
        Student student = p.getStudent();
        Medicine medicine = p.getMedicine();

        return new PrescriptionDTO(
                p.getId(),
                student != null ? student.getStudentCode() : "Không rõ",
                student != null ? student.getName() : "Không rõ",
                medicine != null ? medicine.getName() : "Thuốc không tìm thấy",
                p.getDosage(),
                p.getUsageInstructions(),
                p.getConsent(),
                p.getDatePrescribed(),
                p.isConfirmedDose()
        );
    }
}
