package ra.hwss1001.model.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class ApiResponse <T>{
    private Boolean success;
    private String message;
    private T data;
    private Meta meta;
    private HttpStatus httpStatus;
}
