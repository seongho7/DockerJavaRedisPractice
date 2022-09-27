package com.sh.adsp.campaign.infrastructure;

import com.sh.adsp.campaign.domain.CampaignAds;
import com.sh.adsp.campaign.domain.CampaignAdsReader;
import com.sh.adsp.campaign.domain.CampaignContents;
import com.sh.adsp.campaign.domain.CampaignContentsReader;
import com.sh.adsp.campaign.domain.CampaignInfo;
import com.sh.adsp.campaign.domain.CampaignMixPolicy;
import com.sh.adsp.campaign.domain.CampaignMixedRetriever;
import com.sh.adsp.campaign.domain.UserId;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.RedisOperations;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class CampaignMixedRetrieverImpl implements CampaignMixedRetriever {
  private final RedisOperations<String, Long> adRedis;
  private final CampaignAdsReader campaignAdsReader;
  private final CampaignContentsReader campaignContentsReader;
  private final CampaignMixPolicy campaignMixPolicy;

  public List<CampaignInfo> retrieve(int adLimit, int contentsLimit, UserId userId) {
    return this.campaignMixPolicy.mix(userId, getRandomCampaignAds(adLimit), getRandomCampaignContents(contentsLimit));
  }

  private List<CampaignAds> getRandomCampaignAds(int adLimit) {
    List<Long> adIds = this.adRedis.opsForZSet().randomMembers(RedisKey.CAMPAIGN_ADS, adLimit);
    return campaignAdsReader.loadByIds(adIds, false);
  }

  private List<CampaignContents> getRandomCampaignContents(int contentsLimit) {
    List<Long> contentsIds = this.adRedis.opsForZSet().randomMembers(RedisKey.CAMPAIGN_CONTENTS, contentsLimit);
    return campaignContentsReader.loadByIds(contentsIds, false);
  }
}
