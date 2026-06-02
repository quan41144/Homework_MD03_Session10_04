package ra.hwss1001.service.impl;

import lombok.RequiredArgsConstructor;
import org.apache.coyote.BadRequestException;
import org.springframework.stereotype.Service;
import ra.hwss1001.exception.ResourceNotFoundException;
import ra.hwss1001.model.dto.request.MedicalRecordCreateDTO;
import ra.hwss1001.model.dto.request.MedicalRecordUpdateDTO;
import ra.hwss1001.model.entity.Doctor;
import ra.hwss1001.model.entity.MedicalRecord;
import ra.hwss1001.model.entity.Patient;
import ra.hwss1001.repository.DoctorRepository;
import ra.hwss1001.repository.MedicalRecordRepository;
import ra.hwss1001.repository.PatientRepository;
import ra.hwss1001.service.MedicalRecordService;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MedicalRecordServiceImpl implements MedicalRecordService {
    private final MedicalRecordRepository medicalRecordRepository;
    private final PatientRepository patientRepository;
    private final DoctorRepository doctorRepository;

    @Override
    public List<MedicalRecord> findAll() {
        return medicalRecordRepository.findAll();
    }

    @Override
    public MedicalRecord findById(Long id) {
        return medicalRecordRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("medical record not found"));
    }

    @Override
    public MedicalRecord createMedicalRecord(MedicalRecordCreateDTO medicalRecordCreateDTO) {
        Patient patient = patientRepository.findById(medicalRecordCreateDTO.getPatientId())
                .orElseThrow(() -> new ResourceNotFoundException("Patient not found."));
        Doctor doctor = doctorRepository.findById(medicalRecordCreateDTO.getDoctorId())
                .orElseThrow(() -> new ResourceNotFoundException("Doctor not found."));
        MedicalRecord medicalRecord = MedicalRecord.builder()
                .medicalRecordDate(medicalRecordCreateDTO.getMedicalRecordDate())
                .patient(patient)
                .patientName(medicalRecordCreateDTO.getPatientName())
                .doctor(doctor)
                .build();
        return medicalRecordRepository.save(medicalRecord);
    }

    @Override
    public MedicalRecord updateMedicalRecord(Long id, MedicalRecordUpdateDTO medicalRecordUpdateDTO) {
        MedicalRecord medicalRecord = medicalRecordRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Medical Record not found."));
        medicalRecord.setSymptoms(medicalRecordUpdateDTO.getSymptoms());
        medicalRecord.setDiagnosis(medicalRecordUpdateDTO.getDiagnosis());
        return medicalRecordRepository.save(medicalRecord);
    }

    @Override
    public void deleteMedicalRecord(Long id) {
        MedicalRecord medicalRecord = medicalRecordRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Medical Record not found."));
        if (medicalRecord.getPrescriptions() != null && !medicalRecord.getPrescriptions().isEmpty()) {
            throw new RuntimeException("Prescription already exists.");
        }
        medicalRecordRepository.deleteById(id);
    }

    @Override
    public List<MedicalRecord> getMedicalRecordsByPatientId(Long patientId) {
        return medicalRecordRepository.findByPatientId(patientId);
    }

    @Override
    public List<MedicalRecord> getMedicalRecordsByDoctorId(Long doctorId) {
        return medicalRecordRepository.findByDoctorId(doctorId);
    }
}
