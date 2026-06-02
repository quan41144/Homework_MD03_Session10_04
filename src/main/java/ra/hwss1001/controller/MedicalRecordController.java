package ra.hwss1001.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ra.hwss1001.model.dto.request.MedicalRecordCreateDTO;
import ra.hwss1001.model.dto.request.MedicalRecordUpdateDTO;
import ra.hwss1001.model.dto.response.ApiDataResponse;
import ra.hwss1001.model.entity.MedicalRecord;
import ra.hwss1001.service.MedicalRecordService;

import java.util.List;

@RestController
@RequestMapping("/api/v1/medical-records")
@RequiredArgsConstructor
public class MedicalRecordController {
    private final MedicalRecordService medicalRecordService;
    @GetMapping
    public ResponseEntity<ApiDataResponse<List<MedicalRecord>>> getAllMedicalRecords() {
        return new ResponseEntity<>(new ApiDataResponse<>(
                true,
                "Lấy danh sách tất cả bệnh án thành công!",
                medicalRecordService.findAll(),
                HttpStatus.OK
        ), HttpStatus.OK);
    }
    @GetMapping("/{id}")
    public ResponseEntity<ApiDataResponse<MedicalRecord>> getMedicalRecordById(@Valid @PathVariable Long id) {
        return new ResponseEntity<>(new ApiDataResponse<>(
                true,
                "Lấy thông tin chi tiết bệnh án ID " + id + " thành công!",
                medicalRecordService.findById(id),
                HttpStatus.OK
        ), HttpStatus.OK);
    }
    @PostMapping
    public ResponseEntity<ApiDataResponse<MedicalRecord>> createMedicalRecord(@Valid @RequestBody MedicalRecordCreateDTO medicalRecordCreateDTO) {
        return new ResponseEntity<>(new ApiDataResponse<>(
                true,
                "Đăng ký lịch khám thành công!",
                medicalRecordService.createMedicalRecord(medicalRecordCreateDTO),
                HttpStatus.CREATED
        ), HttpStatus.CREATED);
    }
    @PutMapping("/{id}")
    public ResponseEntity<ApiDataResponse<MedicalRecord>> updateMedicalRecord(@Valid @RequestBody MedicalRecordUpdateDTO medicalRecordUpdateDTO,@Valid @PathVariable Long id) {
        return new ResponseEntity<>(new ApiDataResponse<>(
                true,
                "Cập nhật thông tin bệnh án thành công!",
                medicalRecordService.updateMedicalRecord(id, medicalRecordUpdateDTO),
                HttpStatus.OK
        ), HttpStatus.OK);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<ApiDataResponse<MedicalRecord>> deleteMedicalRecord(@Valid @PathVariable Long id) {
        medicalRecordService.deleteMedicalRecord(id);
        return new ResponseEntity<>(new ApiDataResponse<>(
                true,
                "Xóa bệnh án thành công!",
                null,
                HttpStatus.OK
        ), HttpStatus.OK);
    }
    @GetMapping("/patient/{id}")
    public ResponseEntity<ApiDataResponse<List<MedicalRecord>>> getMedicalRecordByPatientId(@Valid @PathVariable Long id) {
        return new ResponseEntity<>(new ApiDataResponse<>(
                true,
                "Lấy thông tin của tất cả bệnh án theo bệnh nhân có ID " + id + " thành công!",
                medicalRecordService.getMedicalRecordsByPatientId(id),
                HttpStatus.OK
        ), HttpStatus.OK);
    }
    //Lấy tất cả lịch hẹn của một bác sĩ cụ thể (theo doctorId)
    @GetMapping("/doctor/{id}")
    public ResponseEntity<ApiDataResponse<List<MedicalRecord>>> getMedicalRecordByDoctorId(@Valid @PathVariable Long id) {
        return new ResponseEntity<>(new ApiDataResponse<>(
                true,
                "Lấy thông tin của tất cả bệnh án theo bác sĩ có ID " + id + " thành công!",
                medicalRecordService.getMedicalRecordsByDoctorId(id),
                HttpStatus.OK
        ), HttpStatus.OK);
    }
}
