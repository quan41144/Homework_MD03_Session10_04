package ra.hwss1001.service;

import ra.hwss1001.model.dto.request.PrescriptionDTO;
import ra.hwss1001.model.entity.Prescription;

public interface PrescriptionService {
    Prescription findById(Long patientId, Long medicalRecordId, Long prescriptionId);
    Prescription createPrescription(Long patientId, PrescriptionDTO prescriptionDTO);
}
