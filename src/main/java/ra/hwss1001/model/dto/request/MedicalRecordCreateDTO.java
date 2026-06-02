package ra.hwss1001.model.dto.request;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class MedicalRecordCreateDTO {
    @Future(message = "Vui lòng đặt lịch không phải ngày trong quá khứ!")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy")
    private LocalDate medicalRecordDate;
    @NotNull(message = "Không được để trống ID của bệnh nhân!")
    private Long patientId;
    @NotBlank(message = "Không được để trống tên bệnh nhân!")
    private String patientName;
    @NotNull(message = "Vui lòng chọn nhập ID của bác sĩ muốn khám!")
    private Long doctorId;
}
