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
@Table(name = "patients")
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class Patient {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "full_name", nullable = false, length = 100)
    private String fullName;
    @Column(name = "birth_of_date")
    private LocalDate bod;
    private Integer age;
    @Column(nullable = false)
    private Gender gender;
    @Column(length = 15, unique = true, nullable = false)
    private String phone;
    @Column(length = 200, unique = true, nullable = false)
    private String email;
    @Column(length = 200)
    private String address;
    @OneToMany(mappedBy = "patient", cascade = CascadeType.ALL)
    @JsonIgnore
    private List<MedicalRecord> medicalRecords;
}
