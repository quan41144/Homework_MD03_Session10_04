package ra.hwss1001.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ra.hwss1001.exception.ResourceNotFoundException;
import ra.hwss1001.model.dto.request.PatientDTO;
import ra.hwss1001.model.entity.Patient;
import ra.hwss1001.repository.PatientRepository;
import ra.hwss1001.service.PatientService;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PatientServiceImpl implements PatientService {
    private final PatientRepository patientRepository;
    @Override
    public List<Patient> findAll() {
        return patientRepository.findAll();
    }

    @Override
    public Patient findById(Long id) {
        return patientRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Patient not found!"));
    }

    @Override
    public Patient createPatient(PatientDTO patientDTO) {
        Patient patient = Patient.builder()
                .fullName(patientDTO.getFullName())
                .bod(patientDTO.getBod())
                .age(patientDTO.getAge())
                .gender(patientDTO.getGender())
                .phone(patientDTO.getPhone())
                .email(patientDTO.getEmail())
                .address(patientDTO.getAddress())
                .build();
        return patientRepository.save(patient);
    }

    @Override
    public Patient updatePatient(Long id, PatientDTO patientDTO) {
        Patient patient = patientRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Patient not found!"));
        patient.setFullName(patientDTO.getFullName());
        patient.setBod(patientDTO.getBod());
        patient.setAge(patientDTO.getAge());
        patient.setGender(patientDTO.getGender());
        patient.setPhone(patientDTO.getPhone());
        patient.setEmail(patientDTO.getEmail());
        patient.setAddress(patientDTO.getAddress());
        return patientRepository.save(patient);
    }

    @Override
    public void deleteByPatientId(Long id) {
        Patient patient = patientRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Patient not found!"));
        if (patient.getMedicalRecords() != null && !patient.getMedicalRecords().isEmpty()) {
            throw new RuntimeException("This doctor must not delete!");
        }
        patientRepository.deleteById(id);
    }
}
