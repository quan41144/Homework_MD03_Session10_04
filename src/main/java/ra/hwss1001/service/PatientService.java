package ra.hwss1001.service;

import ra.hwss1001.model.dto.request.PatientDTO;
import ra.hwss1001.model.entity.Patient;

import java.util.List;

public interface PatientService {
    List<Patient> findAll();
    Patient findById(Long id);
    Patient createPatient(PatientDTO patientDTO);
    Patient updatePatient(Long id, PatientDTO patientDTO);
    void deleteByPatientId(Long id);
}
