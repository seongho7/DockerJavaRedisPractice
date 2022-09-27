package com.sh.adsp.core.exception;

import com.sh.adsp.core.response.ErrorCode;

public class EntityNotFoundException extends BaseException {
  public EntityNotFoundException() {
    super(ErrorCode.COMMON_INVALID_PARAMETER);
  }

  public EntityNotFoundException(String message) {
    super(message, ErrorCode.COMMON_INVALID_PARAMETER);
  }
}
