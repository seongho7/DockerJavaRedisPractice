package com.sh.adsp.campaign.presentation.rest;

import com.sh.adsp.campaign.application.CampaignQueryFacade;
import com.sh.adsp.campaign.domain.CampaignAds;
import com.sh.adsp.campaign.presentation.rest.AdQueryReq.Sort;
import com.sh.adsp.core.response.CommonResponse;
import java.util.List;
import java.util.stream.Collectors;
import javax.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RequestMapping({CampaignApiUrl.AD_QUERY})
@RestController
public class AdQueryApi {
  private final CampaignQueryFacade facade;

  @GetMapping
  CommonResponse<List<AdQueryRes>> get(@Valid AdQueryReq req) {
    List<CampaignAds> ads;
    if (req.getSort() == Sort.random) {
      ads = facade.getAdsAsRandom(req.getLimit());
    } else {
      ads = facade.getAdsAsPriority(req.getLimit());
    }

    return CommonResponse.success(ads.stream().map(AdQueryRes::new).collect(Collectors.toList()));
  }
}
