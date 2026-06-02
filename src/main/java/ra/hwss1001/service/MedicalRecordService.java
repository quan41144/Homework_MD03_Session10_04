package ra.hwss1001.service;

import ra.hwss1001.model.dto.request.MedicalRecordCreateDTO;
import ra.hwss1001.model.dto.request.MedicalRecordUpdateDTO;
import ra.hwss1001.model.entity.MedicalRecord;

import java.util.List;

public interface MedicalRecordService {
    List<MedicalRecord> findAll();
    MedicalRecord findById(Long id);
    MedicalRecord createMedicalRecord(MedicalRecordCreateDTO medicalRecordCreateDTO);
    MedicalRecord updateMedicalRecord(Long id, MedicalRecordUpdateDTO medicalRecordUpdateDTO);
    void deleteMedicalRecord(Long id);
    List<MedicalRecord> getMedicalRecordsByPatientId(Long patientId);
    List<MedicalRecord> getMedicalRecordsByDoctorId(Long doctorId);
}
