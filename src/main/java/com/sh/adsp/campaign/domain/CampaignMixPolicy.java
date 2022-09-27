package com.sh.adsp.campaign.domain;

import java.util.List;

public interface CampaignMixPolicy {
  List<CampaignInfo> mix(UserId userId, List<CampaignAds> ads, List<CampaignContents> contentsList);
}
