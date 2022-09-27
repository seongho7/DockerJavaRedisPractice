package com.sh.adsp.campaign.presentation.rest;

import com.sh.adsp.campaign.application.CampaignQueryFacade;
import com.sh.adsp.campaign.domain.CampaignInfo;
import com.sh.adsp.campaign.domain.UserId;
import com.sh.adsp.core.response.CommonResponse;
import java.util.List;
import java.util.stream.Collectors;
import javax.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RequestMapping({CampaignApiUrl.CAMPAIGN_QUERY})
@RestController
public class CampaignQueryApi {
  private final CampaignQueryFacade campaignQueryFacade;

  @GetMapping
  CommonResponse<List<CampaignQueryRes>> get(@Valid CampaignQueryReq req) {
    List<CampaignInfo> campaignInfos = campaignQueryFacade.getCampaignAsMixed(req.getAdLimit(), req.getContentsLimit(), new UserId(req.getUserId()));
    return CommonResponse.success(campaignInfos.stream().map(CampaignQueryRes::new).collect(Collectors.toList()));
  }
}
