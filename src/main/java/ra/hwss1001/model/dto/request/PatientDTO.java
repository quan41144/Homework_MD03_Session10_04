package ra.hwss1001.model.dto.request;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import ra.hwss1001.model.entity.Gender;

import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class PatientDTO {
    @NotBlank(message = "Không được để trống tên bệnh nhân!")
    private String fullName;
    @NotNull(message = "Không được để trống ngày sinh!")
    @PastOrPresent(message = "Ngày sinh không đúng!")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy")
    private LocalDate bod;
    @Min(value = 0, message = "Tuổi không được âm!")
    private Integer age;
    private Gender gender;
    @NotBlank(message = "Không được để trống số điện thoại!")
    @Pattern(regexp = "(0)\\d{9}", message = "Không đúng định dạng số điện thoại!")
    private String phone;
    @NotBlank(message = "Không được để trống email!")
    @Email(message = "Không đúng định dạng email!")
    private String email;
    private String address;
}
