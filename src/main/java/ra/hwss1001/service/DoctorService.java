package ra.hwss1001.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import ra.hwss1001.model.dto.request.DoctorDTO;
import ra.hwss1001.model.entity.Doctor;

import java.util.List;

public interface DoctorService {
    Page<Doctor> findAll(Pageable pageable);
    Doctor findById(long id);
    Doctor createDoctor(DoctorDTO doctorDTO);
    Doctor updateDoctor(Long id, DoctorDTO doctorDTO);
    void deleteDoctor(Long id);
}
