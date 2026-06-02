package ra.hwss1001.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ra.hwss1001.model.entity.Patient;
@Repository
public interface PatientRepository extends JpaRepository<Patient, Long> {
}
