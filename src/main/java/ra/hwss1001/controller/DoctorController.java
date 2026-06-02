package ra.hwss1001.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ra.hwss1001.model.dto.request.DoctorDTO;
import ra.hwss1001.model.dto.response.ApiDataResponse;
import ra.hwss1001.model.entity.Doctor;
import ra.hwss1001.service.DoctorService;

import java.util.List;

@RestController
@RequestMapping("/api/v1/doctors")
@RequiredArgsConstructor
public class DoctorController {
    private final DoctorService doctorService;
    @GetMapping
    public ResponseEntity<ApiDataResponse<List<Doctor>>> getAllDoctors() {
        return new ResponseEntity<>(new ApiDataResponse<>(
                true,
                "Lấy danh sách toàn bộ bác sĩ thành công!",
                doctorService.findAll(),
                HttpStatus.OK
        ), HttpStatus.OK);
    }
    @PostMapping
    public ResponseEntity<ApiDataResponse<Doctor>> createDoctor(@Valid @RequestBody DoctorDTO doctorDTO) {
        return new ResponseEntity<>(new ApiDataResponse<>(
                true,
                "Thêm mới một bác sĩ thành công!",
                doctorService.createDoctor(doctorDTO),
                HttpStatus.CREATED
        ), HttpStatus.CREATED);
    }
    @GetMapping("/{id}")
    public ResponseEntity<ApiDataResponse<Doctor>> getDoctorById(@Valid @PathVariable Long id) {
        return new ResponseEntity<>(new ApiDataResponse<>(
                true,
                "Lấy thông tin chi tiết bác sĩ có ID " + id + " thành công!",
                doctorService.findById(id),
                HttpStatus.OK
        ), HttpStatus.OK);
    }
    @PutMapping("/{id}")
    public ResponseEntity<ApiDataResponse<Doctor>> updateDoctor(@Valid @PathVariable Long id, @Valid @RequestBody DoctorDTO doctorDTO) {
        return new ResponseEntity<>(new ApiDataResponse<>(
                true,
                "Cập nhật thông tin bác sĩ ID " + id + " thành công!",
                doctorService.updateDoctor(id, doctorDTO),
                HttpStatus.OK
        ), HttpStatus.OK);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<ApiDataResponse<Doctor>> deleteDoctor(@Valid @PathVariable Long id) {
        doctorService.deleteDoctor(id);
        return new ResponseEntity<>(new ApiDataResponse<>(
                true,
                "Xóa bác sĩ ID " + id + " thành công!",
                null,
                HttpStatus.OK
        ), HttpStatus.OK);
    }
}
