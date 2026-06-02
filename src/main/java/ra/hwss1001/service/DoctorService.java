package ra.hwss1001.service;

import ra.hwss1001.model.dto.request.DoctorDTO;
import ra.hwss1001.model.entity.Doctor;

import java.util.List;

public interface DoctorService {
    List<Doctor> findAll();
    Doctor findById(long id);
    Doctor createDoctor(DoctorDTO doctorDTO);
    Doctor updateDoctor(Long id, DoctorDTO doctorDTO);
    void deleteDoctor(Long id);
}
