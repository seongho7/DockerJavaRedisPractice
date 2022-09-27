package com.sh.adsp.campaign.presentation.rest;

import com.sh.adsp.campaign.domain.CampaignInfo;

record CampaignQueryRes(long id, String name, String imageUrl, String landingUrl, CampaignInfo.Type type) {
  CampaignQueryRes(CampaignInfo info) {
    this(info.getId(), info.getName(), info.getImageUrl(), info.getLandingUrl(), info.getType());
  }
}
