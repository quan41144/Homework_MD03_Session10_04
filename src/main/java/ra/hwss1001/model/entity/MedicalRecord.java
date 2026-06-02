package ra.hwss1001.model.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "medical_records")
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class MedicalRecord {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "medical_record_date")
    private LocalDate medicalRecordDate;
    @Column(name = "patient_name", length = 100, nullable = false)
    private String patientName;
    @Column(columnDefinition = "text")
    private String symptoms;
    @Column(columnDefinition = "text")
    private String diagnosis;
    @OneToMany(mappedBy = "medicalRecord",cascade = CascadeType.ALL)
    private List<Prescription> prescriptions;
    @ManyToOne
    @JoinColumn(name = "doctor_id", nullable = false)
    private Doctor doctor;
    @ManyToOne
    @JoinColumn(name = "patient_id", nullable = false)
    private Patient patient;
}
