package ra.hwss1001.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ra.hwss1001.exception.ResourceNotFoundException;
import ra.hwss1001.model.dto.request.PrescriptionDTO;
import ra.hwss1001.model.entity.MedicalRecord;
import ra.hwss1001.model.entity.Patient;
import ra.hwss1001.model.entity.Prescription;
import ra.hwss1001.repository.MedicalRecordRepository;
import ra.hwss1001.repository.PatientRepository;
import ra.hwss1001.repository.PrescriptionRepository;
import ra.hwss1001.service.PrescriptionService;

@Service
@RequiredArgsConstructor
public class PrescriptionServiceImpl implements PrescriptionService {
    private final PrescriptionRepository prescriptionRepository;
    private final MedicalRecordRepository medicalRecordRepository;
    private final PatientRepository patientRepository;

    @Override
    public Prescription findById(Long patientId, Long medicalRecordId, Long prescriptionId) {
        MedicalRecord medicalRecord = medicalRecordRepository.findById(medicalRecordId)
                .orElseThrow(() -> new ResourceNotFoundException("Medical Record not found with id: " + medicalRecordId));

        Prescription prescription = prescriptionRepository.findById(prescriptionId)
                .orElseThrow(() -> new ResourceNotFoundException("Prescription not found with id: " + prescriptionId));
        if (!(medicalRecord.getPatient().getId().equals(patientId) && prescription.getMedicalRecord().getId().equals(medicalRecordId))) {
            throw new RuntimeException("Đơn thuốc không khớp với bệnh án và bệnh nhân!");
        }
        return prescription;
    }

    @Override
    public Prescription createPrescription(Long patientId, PrescriptionDTO prescriptionDTO) {
        Patient patient = patientRepository.findById(patientId)
                .orElseThrow(() -> new ResourceNotFoundException("Patient not found with id: " + patientId));
        MedicalRecord medicalRecord = medicalRecordRepository.findById(prescriptionDTO.getMedicalRecordId())
                .orElseThrow(() -> new ResourceNotFoundException("Medical Record not found with id: " + prescriptionDTO.getMedicalRecordId()));
        if (!medicalRecord.getPatient().getId().equals(patientId)) {
            throw new RuntimeException("Bệnh án này không khớp với bệnh nhân!");
        }
        Prescription prescription = Prescription.builder()
                .medicinesText(prescriptionDTO.getMedicinesText())
                .medicalRecord(medicalRecord)
                .build();
        return prescriptionRepository.save(prescription);
    }
}
