package ra.hwss1001.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ra.hwss1001.model.dto.request.PatientDTO;
import ra.hwss1001.model.dto.response.ApiDataResponse;
import ra.hwss1001.model.entity.Patient;
import ra.hwss1001.service.PatientService;

import java.util.List;

@RestController
@RequestMapping("/api/v1/patients")
@RequiredArgsConstructor
public class PatientController {
    private final PatientService patientService;
    @GetMapping
    public ResponseEntity<ApiDataResponse<List<Patient>>> getAllPatients() {
        return new ResponseEntity<>(new ApiDataResponse<>(
                true,
                "Lấy danh sách bệnh nhân thành công!",
                patientService.findAll(),
                HttpStatus.OK
        ), HttpStatus.OK);
    }
    @GetMapping("/{id}")
    public ResponseEntity<ApiDataResponse<Patient>> getPatientById(@Valid @PathVariable Long id) {
        return new ResponseEntity<>(new ApiDataResponse<>(
                true,
                "Lấy thông tin chi tiết của bệnh nhân ID " + id + " thành công!",
                patientService.findById(id),
                HttpStatus.OK
        ), HttpStatus.OK);
    }
    @PostMapping
    public ResponseEntity<ApiDataResponse<Patient>> createPatient(@Valid @RequestBody PatientDTO patientDTO) {
        return new ResponseEntity<>(new ApiDataResponse<>(
                true,
                "Thêm mới bệnh nhân thành công!",
                patientService.createPatient(patientDTO),
                HttpStatus.CREATED
        ), HttpStatus.CREATED);
    }
    @PutMapping("/{id}")
    public ResponseEntity<ApiDataResponse<Patient>> updatePatient(@Valid @PathVariable Long id, @Valid @RequestBody PatientDTO patientDTO) {
        return new ResponseEntity<>(new ApiDataResponse<>(
                true,
                "Cập nhật thông tin bệnh nhân thành công!",
                patientService.updatePatient(id, patientDTO),
                HttpStatus.OK
        ), HttpStatus.OK);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<ApiDataResponse<Patient>> deletePatient(@Valid @PathVariable Long id) {
        patientService.deleteByPatientId(id);
        return new ResponseEntity<>(new ApiDataResponse<>(
                true,
                "Xóa bệnh nhân thành công!",
                null,
                HttpStatus.OK
        ), HttpStatus.OK);
    }
}
