package com.sh.adsp.campaign.presentation.rest;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter @Setter @ToString
class AdQueryReq {
  @NotNull
  private Sort sort;
  @Min(10L) @Max(30L)
  private int limit = 10;

  public enum Sort {
    random,
    priority;
  }
}
