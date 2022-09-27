package com.sh.adsp.campaign.presentation.rest;

import com.sh.adsp.campaign.domain.CampaignAds;

record AdQueryRes(long id, String name, String imageUrl, String landingUrl) {
  AdQueryRes(CampaignAds ad) {
    this(ad.getId(), ad.getName(), ad.getImageUrl(), ad.getLandingUrl());
  }
}
