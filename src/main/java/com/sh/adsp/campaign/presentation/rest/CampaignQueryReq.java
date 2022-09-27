package com.sh.adsp.campaign.presentation.rest;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@ToString
@Getter @Setter
final class CampaignQueryReq {
  @NotNull
  private Sort sort;
  @Min(1)
  private long userId;
  @Min(3L) @Max(20L)
  private int adLimit = 3;
  @Min(3L) @Max(20L)
  private int contentsLimit = 3;

  enum Sort {
    mixed;
  }

}
