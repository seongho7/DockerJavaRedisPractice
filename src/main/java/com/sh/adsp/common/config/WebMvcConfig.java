package com.sh.adsp.common.config;

import com.sh.adsp.common.interceptor.CommonHttpRequestInterceptor;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@RequiredArgsConstructor
@Configuration
public class WebMvcConfig implements WebMvcConfigurer {
  private final CommonHttpRequestInterceptor commonHttpRequestInterceptor;

  public void addInterceptors(InterceptorRegistry registry) {
    registry.addInterceptor(this.commonHttpRequestInterceptor);
  }
}
