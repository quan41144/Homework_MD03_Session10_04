package ra.hwss1001.model.dto.response;

import lombok.*;
import org.springframework.http.HttpStatus;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class ApiDataResponse <T>{
    private Boolean success;
    private String message;
    private T data;
    private HttpStatus httpStatus;
}
