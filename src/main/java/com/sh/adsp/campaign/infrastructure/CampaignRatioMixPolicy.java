package com.sh.adsp.campaign.infrastructure;

import com.sh.adsp.campaign.domain.AdContentsRatio;
import com.sh.adsp.campaign.domain.CampaignAds;
import com.sh.adsp.campaign.domain.CampaignContents;
import com.sh.adsp.campaign.domain.CampaignInfo;
import com.sh.adsp.campaign.domain.CampaignMixPolicy;
import com.sh.adsp.campaign.domain.UserId;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisOperations;
import org.springframework.stereotype.Component;

@Component
public class CampaignRatioMixPolicy implements CampaignMixPolicy {
  private AdContentsRatio adContentsRatio;
  private final HashOperations<String, Long, Integer> hashOperations;

  public CampaignRatioMixPolicy(RedisOperations<String, Long> adRedis) {
    this.hashOperations = adRedis.opsForHash();
  }

  public List<CampaignInfo> mix(UserId userId, List<CampaignAds> ads, List<CampaignContents> contentsList) {
    List<CampaignInfo> campaignInfos = mergeAdAndContents(ads, contentsList);
    Collections.shuffle(campaignInfos, ThreadLocalRandom.current());
    int reqCycleValue = adContentsRatio.nextCycleValue(hashOperations.get(RedisKey.USER_REQUEST_CYCLE, userId.userId()));
    checkAdTurnAndSwap(reqCycleValue, campaignInfos);
    checkContentsTurnAndSwap(reqCycleValue, campaignInfos);
    saveRequestCycleValue(userId, reqCycleValue);
    return campaignInfos;
  }

  private List<CampaignInfo> mergeAdAndContents(List<CampaignAds> ads, List<CampaignContents> contentsList) {
    List<CampaignInfo> campaignInfos = ads.stream().map(CampaignInfo::new).collect(Collectors.toList());
    List<CampaignInfo> contentsInfos = contentsList.stream().map(CampaignInfo::new).toList();
    campaignInfos.addAll(contentsInfos);
    return campaignInfos;
  }

  private void checkAdTurnAndSwap(int reqCycleValue, List<CampaignInfo> campaignInfos) {
    if (this.adContentsRatio.isAdTurn(reqCycleValue) && !campaignInfos.get(0).isAd()) {
      Collections.swap(campaignInfos, 0,
          IntStream.range(0, campaignInfos.size()).filter((i) -> campaignInfos.get(i).isAd()).findFirst().getAsInt());
    }
  }

  private void checkContentsTurnAndSwap(int reqCycleValue, List<CampaignInfo> campaignInfos) {
    if (this.adContentsRatio.isContentsTurn(reqCycleValue) && !campaignInfos.get(0).isContents()) {
      Collections.swap(campaignInfos, 0,
          IntStream.range(0, campaignInfos.size()).filter((i) -> campaignInfos.get(i).isContents()).findFirst().getAsInt());
    }
  }

  private void saveRequestCycleValue(UserId userId, int reqCycleValue) {
    this.hashOperations.put(RedisKey.USER_REQUEST_CYCLE, userId.userId(), reqCycleValue);
  }

  protected void refreshAdContentsRatio(AdContentsRatio adContentsRatio) {
    this.adContentsRatio = adContentsRatio;
  }
}
