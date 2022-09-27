package com.sh.adsp.campaign.domain;

import java.util.List;

public interface CampaignAdsReader {
  List<CampaignAds> loadByIds(List<Long> ids, boolean orderByInput);

  List<CampaignAds> loadByPriority(int firstDisplayPriority);
}
