package ra.hwss1001.model.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "doctors")
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class Doctor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "full_name", length = 100, nullable = false)
    private String fullName;
    @Column(name = "birth_of_date")
    private LocalDate bod;
    private Integer age;
    private Gender gender;
    @Column(length = 100, nullable = false)
    private String speciality;
    @Column(nullable = false, length = 15, unique = true)
    private String phone;
    @Column(nullable = false, length = 200, unique = true)
    private String email;
    @Column(columnDefinition = "text")
    private String address;
    @OneToMany(mappedBy = "doctor", cascade = CascadeType.ALL)
    @JsonIgnore
    private List<MedicalRecord> medicalRecords;
}
