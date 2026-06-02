package ra.hwss1001.advice;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.transaction.CannotCreateTransactionException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import ra.hwss1001.exception.ResourceNotFoundException;
import ra.hwss1001.model.dto.response.ApiDataResponse;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ApiDataResponse<?>> handleResourceNotFoundException(ResourceNotFoundException e) {
        return new ResponseEntity<>(new ApiDataResponse<>(
                false,
                e.getMessage(),
                null,
                HttpStatus.NOT_FOUND
        ), HttpStatus.NOT_FOUND);
    }
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ApiDataResponse<Map<String, String>>> handleMethodArgumentNotValidException(MethodArgumentNotValidException e) {
        Map<String, String> errors = new HashMap<>();
        e.getBindingResult().getAllErrors().forEach((error) ->
                errors.put(error.getObjectName(), error.getDefaultMessage())
        );
        return new ResponseEntity<>(new ApiDataResponse<>(
                false,
                "Dữ liệu không hợp lệ!",
                errors,
                HttpStatus.BAD_REQUEST
        ), HttpStatus.BAD_REQUEST);
    }
    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<ApiDataResponse<Map<String, String>>> handleHttpMessageNotReadableException(HttpMessageNotReadableException e) {
        Map<String, String> errors = new HashMap<>();
        errors.put(e.getLocalizedMessage(), e.getMessage());
        return new ResponseEntity<>(new ApiDataResponse<>(
                false,
                "Sai định dạng dữ liệu nhập vào!",
                errors,
                HttpStatus.BAD_REQUEST
        ), HttpStatus.BAD_REQUEST);
    }
    @ExceptionHandler(CannotCreateTransactionException.class)
    public ResponseEntity<ApiDataResponse<?>> handleCannotCreateTransactionException(CannotCreateTransactionException e) {
        return new ResponseEntity<>(new ApiDataResponse<>(
                false,
                e.getMessage(),
                null,
                HttpStatus.INTERNAL_SERVER_ERROR
        ), HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
