package ra.hwss1001.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ra.hwss1001.model.dto.request.PrescriptionDTO;
import ra.hwss1001.model.dto.response.ApiDataResponse;
import ra.hwss1001.model.entity.Prescription;
import ra.hwss1001.service.PrescriptionService;

@RestController
@RequestMapping("/api/v1/patients/{patientId}")
@RequiredArgsConstructor
public class PrescriptionController {
    private final PrescriptionService prescriptionService;
    @GetMapping("/medical-records/{medicalRecordId}/prescriptions/{prescriptionId}")
    public ResponseEntity<ApiDataResponse<Prescription>> getPrescriptionByPMPID(@Valid @PathVariable Long patientId, @Valid @PathVariable Long medicalRecordId, @Valid @PathVariable Long prescriptionId) {
        return new ResponseEntity<>(new ApiDataResponse<>(
                true,
                "Lấy thành công đơn thuốc của bệnh nhân ID " + patientId,
                prescriptionService.findById(patientId, medicalRecordId, prescriptionId),
                HttpStatus.OK
        ), HttpStatus.OK);
    }
    @PostMapping("/prescriptions")
    public ResponseEntity<ApiDataResponse<Prescription>> createPrescription(@Valid @RequestBody PrescriptionDTO prescriptionDTO,@Valid @PathVariable Long patientId) {
        return new ResponseEntity<>(new ApiDataResponse<>(
                true,
                "Thêm thành công đơn thuốc mới cho bệnh nhân ID " + patientId,
                prescriptionService.createPrescription(patientId, prescriptionDTO),
                HttpStatus.CREATED
        ), HttpStatus.CREATED);
    }
}
