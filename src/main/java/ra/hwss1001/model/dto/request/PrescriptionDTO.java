package ra.hwss1001.model.dto.request;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class PrescriptionDTO {
    private String medicinesText;
    @NotNull(message = "Không được để trống ID của bệnh án cần được kê đơn thuốc!")
    private Long medicalRecordId;
}
