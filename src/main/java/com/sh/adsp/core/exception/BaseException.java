package com.sh.adsp.core.exception;

import com.sh.adsp.core.response.ErrorCode;

public class BaseException extends RuntimeException {
  private ErrorCode errorCode;

  public BaseException() {
  }

  public BaseException(ErrorCode errorCode) {
    super(errorCode.getErrorMsg(new Object[0]));
    this.errorCode = errorCode;
  }

  public BaseException(String message, ErrorCode errorCode) {
    super(message);
    this.errorCode = errorCode;
  }

  public BaseException(String message, ErrorCode errorCode, Throwable cause) {
    super(message, cause);
    this.errorCode = errorCode;
  }

  public ErrorCode getErrorCode() {
    return this.errorCode;
  }
}
