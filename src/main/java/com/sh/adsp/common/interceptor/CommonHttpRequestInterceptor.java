package com.sh.adsp.common.interceptor;

import java.util.UUID;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.MDC;
import org.springframework.stereotype.Component;
import org.springframework.util.ObjectUtils;
import org.springframework.web.servlet.HandlerInterceptor;

@Component
public class CommonHttpRequestInterceptor implements HandlerInterceptor {
  private static final Logger log = LoggerFactory.getLogger(CommonHttpRequestInterceptor.class);
  public static final String HEADER_REQUEST_UUID_KEY = "x-request-id";

  public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
    String requestEventId = request.getHeader(HEADER_REQUEST_UUID_KEY);
    if (ObjectUtils.isEmpty(requestEventId)) {
      requestEventId = UUID.randomUUID().toString();
    }

    MDC.put(HEADER_REQUEST_UUID_KEY, requestEventId);
    return true;
  }
}
