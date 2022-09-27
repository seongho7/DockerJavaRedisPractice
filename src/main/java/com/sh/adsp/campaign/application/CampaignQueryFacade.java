package com.sh.adsp.campaign.application;

import com.sh.adsp.campaign.domain.AdContentsRatio;
import com.sh.adsp.campaign.domain.AdContentsRatioReader;
import com.sh.adsp.campaign.domain.CampaignAds;
import com.sh.adsp.campaign.domain.CampaignAdsReader;
import com.sh.adsp.campaign.domain.CampaignInfo;
import com.sh.adsp.campaign.domain.CampaignMixedRetriever;
import com.sh.adsp.campaign.domain.FirstCampaignAdWeightedPicker;
import com.sh.adsp.campaign.domain.UserId;
import com.sh.adsp.campaign.infrastructure.RedisKey;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.RedisOperations;
import org.springframework.data.redis.core.ZSetOperations;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class CampaignQueryFacade {
  private final AdContentsRatioReader adContentsRatioReader;
  private final CampaignAdsReader campaignAdsReader;
  private final RedisOperations<String, Long> adRedis;
  private final FirstCampaignAdWeightedPicker firstCampaignAdWeightedPicker;
  private final CampaignMixedRetriever campaignMixedRetriever;

  public AdContentsRatio getAdContentsRatio(AdContentsRatio.Type type) {
    return this.adContentsRatioReader.load(type);
  }

  public List<CampaignAds> getAdsAsRandom(int limit) {
    ZSetOperations<String, Long> zSet = this.adRedis.opsForZSet();
    List<Long> campaignAdIds = zSet.randomMembers(RedisKey.CAMPAIGN_ADS, limit);
    return this.campaignAdsReader.loadByIds(campaignAdIds, true);
  }

  public List<CampaignAds> getAdsAsPriority(int limit) {
    CampaignAds firstAd = this.firstCampaignAdWeightedPicker.pick();
    List<CampaignAds> randomAds = this.getAdsAsRandom(limit - 1);
    randomAds.add(0, firstAd);
    return randomAds;
  }

  public List<CampaignInfo> getCampaignAsMixed(int adLimit, int contentsLimit, UserId userId) {
    return this.campaignMixedRetriever.retrieve(adLimit, contentsLimit, userId);
  }
}
