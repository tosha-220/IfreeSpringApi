package com.ifree.handler;

import com.ifree.exceptions.OrderNotFoundException;
import java.util.HashMap;
import java.util.Map;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@ControllerAdvice(annotations = RestController.class, basePackages = "com.ifree")
public class RestExceptionHandler {


  @ExceptionHandler(value = OrderNotFoundException.class)
  public ResponseEntity<Map<String, Object>> handleOrderNotFoundException(
      OrderNotFoundException exception) {
    return createExceptionDTO(exception, HttpStatus.NOT_FOUND);
  }


  @ExceptionHandler(value = RuntimeException.class)
  public ResponseEntity<Map<String, Object>> handleRuntimeException(RuntimeException exception) {
    return createExceptionDTO(exception, HttpStatus.INTERNAL_SERVER_ERROR);
  }

  private ResponseEntity<Map<String, Object>> createExceptionDTO(Exception ex,
      HttpStatus httpStatus) {
    log.trace("Handle exception", ex);
    Map<String, Object> exceptionDTO = new HashMap<>();
    exceptionDTO.put("message",
        StringUtils.isNotEmpty(ex.getMessage()) ? ex.getMessage() : "Unknown Error");
    exceptionDTO.put("errorType", ex.getClass().getSimpleName());
    if (ex.getCause() != null) {
      exceptionDTO.put("cause",
          StringUtils.isNotEmpty(ex.getCause().getMessage()) ? ex.getCause().getMessage()
              : "Unknown Cause");
    }

    HttpHeaders httpHeaders = new HttpHeaders();
    httpHeaders.setContentType(MediaType.APPLICATION_JSON);
    return new ResponseEntity<>(exceptionDTO, httpHeaders, httpStatus);
  }

}
