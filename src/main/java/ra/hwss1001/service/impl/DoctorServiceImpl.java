package ra.hwss1001.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ra.hwss1001.exception.ResourceNotFoundException;
import ra.hwss1001.model.dto.request.DoctorDTO;
import ra.hwss1001.model.entity.Doctor;
import ra.hwss1001.repository.DoctorRepository;
import ra.hwss1001.service.DoctorService;

import java.util.List;

@Service
@RequiredArgsConstructor
public class DoctorServiceImpl implements DoctorService {
    private final DoctorRepository doctorRepository;
    @Override
    public List<Doctor> findAll() {
        return doctorRepository.findAll();
    }

    @Override
    public Doctor findById(long id) {
        return doctorRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Doctor Not Found"));
    }

    @Override
    public Doctor createDoctor(DoctorDTO doctorDTO) {
        Doctor newDoctor = Doctor.builder()
                .fullName(doctorDTO.getFullName())
                .bod(doctorDTO.getBod())
                .age(doctorDTO.getAge())
                .gender(doctorDTO.getGender())
                .speciality(doctorDTO.getSpeciality())
                .phone(doctorDTO.getPhone())
                .email(doctorDTO.getEmail())
                .address(doctorDTO.getAddress())
                .build();
        return doctorRepository.save(newDoctor);
    }

    @Override
    public Doctor updateDoctor(Long id, DoctorDTO doctorDTO) {
        Doctor updatedDoctor = doctorRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Doctor Not Found"));
        updatedDoctor.setFullName(doctorDTO.getFullName());
        updatedDoctor.setBod(doctorDTO.getBod());
        updatedDoctor.setAge(doctorDTO.getAge());
        updatedDoctor.setGender(doctorDTO.getGender());
        updatedDoctor.setSpeciality(doctorDTO.getSpeciality());
        updatedDoctor.setPhone(doctorDTO.getPhone());
        updatedDoctor.setEmail(doctorDTO.getEmail());
        updatedDoctor.setAddress(doctorDTO.getAddress());
        return doctorRepository.save(updatedDoctor);
    }

    @Override
    public void deleteDoctor(Long id) {
        Doctor doctor = doctorRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Doctor Not Found"));
        if (doctor.getMedicalRecords() != null && !doctor.getMedicalRecords().isEmpty()) {
            throw new RuntimeException("This doctor must not delete!");
        }
        doctorRepository.deleteById(id);
    }
}
