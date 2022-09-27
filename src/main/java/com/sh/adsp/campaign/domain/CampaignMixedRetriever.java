package com.sh.adsp.campaign.domain;

import java.util.List;

public interface CampaignMixedRetriever {
  List<CampaignInfo> retrieve(int adLimit, int contentsLimit, UserId userId);
}