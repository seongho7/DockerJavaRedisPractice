package com.sh.adsp.core.exception;

import com.sh.adsp.core.response.ErrorCode;

public class IllegalStatusException extends BaseException {
  public IllegalStatusException() {
    super(ErrorCode.COMMON_ILLEGAL_STATUS);
  }

  public IllegalStatusException(String message) {
    super(message, ErrorCode.COMMON_ILLEGAL_STATUS);
  }
}
