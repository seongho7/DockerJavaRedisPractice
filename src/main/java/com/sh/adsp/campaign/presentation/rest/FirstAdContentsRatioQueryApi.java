package com.sh.adsp.campaign.presentation.rest;

import com.sh.adsp.campaign.application.CampaignQueryFacade;
import com.sh.adsp.campaign.domain.AdContentsRatio;
import com.sh.adsp.campaign.domain.AdContentsRatio.Type;
import com.sh.adsp.core.response.CommonResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RequestMapping({CampaignApiUrl.CONTENTS_RATIO_QUERY})
@RestController
public class FirstAdContentsRatioQueryApi {
  private final CampaignQueryFacade service;

  @GetMapping
  CommonResponse<FirstAdContentsRatioQueryRes> get() {
    AdContentsRatio ratio = this.service.getAdContentsRatio(Type.FIRST);
    return CommonResponse.success(new FirstAdContentsRatioQueryRes(ratio));
  }
}
