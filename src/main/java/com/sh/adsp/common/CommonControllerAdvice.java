package com.sh.adsp.common;

import com.sh.adsp.core.exception.BaseException;
import com.sh.adsp.core.response.CommonResponse;
import com.sh.adsp.core.response.ErrorCode;
import java.util.ArrayList;
import java.util.List;
import org.apache.catalina.connector.ClientAbortException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.MDC;
import org.springframework.core.NestedExceptionUtils;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class CommonControllerAdvice {
  private static final Logger log = LoggerFactory.getLogger(CommonControllerAdvice.class);
  private static final List<ErrorCode> SPECIFIC_ALERT_TARGET_ERROR_CODE_LIST = new ArrayList();

  public CommonControllerAdvice() {
  }

  @ResponseBody
  @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
  @ExceptionHandler({Exception.class})
  public CommonResponse onException(Exception e) {
    String eventId = MDC.get("x-request-id");
    log.error("eventId = {} ", eventId, e);
    return CommonResponse.fail(ErrorCode.COMMON_SYSTEM_ERROR);
  }

  @ResponseBody
  @ResponseStatus(HttpStatus.OK)
  @ExceptionHandler({BaseException.class})
  public CommonResponse onBaseException(BaseException e) {
    String eventId = MDC.get("x-request-id");
    if (SPECIFIC_ALERT_TARGET_ERROR_CODE_LIST.contains(e.getErrorCode())) {
      log.error("[BaseException] eventId = {}, cause = {}, errorMsg = {}", eventId, NestedExceptionUtils.getMostSpecificCause(e),
          NestedExceptionUtils.getMostSpecificCause(e).getMessage());
    } else {
      log.warn("[BaseException] eventId = {}, cause = {}, errorMsg = {}", eventId, NestedExceptionUtils.getMostSpecificCause(e),
          NestedExceptionUtils.getMostSpecificCause(e).getMessage());
    }

    return CommonResponse.fail(e.getMessage(), e.getErrorCode().name());
  }

  @ResponseBody
  @ResponseStatus(HttpStatus.OK)
  @ExceptionHandler({ClientAbortException.class})
  public CommonResponse skipException(Exception e) {
    String eventId = MDC.get("x-request-id");
    log.warn("[skipException] eventId = {}, cause = {}, errorMsg = {}", eventId, NestedExceptionUtils.getMostSpecificCause(e),
        NestedExceptionUtils.getMostSpecificCause(e).getMessage());
    return CommonResponse.fail(ErrorCode.COMMON_SYSTEM_ERROR);
  }

  @ResponseBody
  @ResponseStatus(HttpStatus.BAD_REQUEST)
  @ExceptionHandler({BindException.class})
  public CommonResponse methodArgumentNotValidException(BindException e) {
    String eventId = MDC.get("x-request-id");
    log.warn("[BaseException] eventId = {}, errorMsg = {}", eventId, NestedExceptionUtils.getMostSpecificCause(e).getMessage());
    BindingResult bindingResult = e.getBindingResult();
    FieldError fe = bindingResult.getFieldError();
    if (fe != null) {
      String var10000 = fe.getField();
      String message = "Request Error " + var10000 + "=" + fe.getRejectedValue() + " (" + fe.getDefaultMessage() + ")";
      return CommonResponse.fail(message, ErrorCode.COMMON_INVALID_PARAMETER.name());
    } else {
      return CommonResponse.fail(ErrorCode.COMMON_INVALID_PARAMETER.getErrorMsg(new Object[0]), ErrorCode.COMMON_INVALID_PARAMETER.name());
    }
  }
}
