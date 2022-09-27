package com.sh.adsp.campaign.infrastructure;

import com.sh.adsp.campaign.domain.CampaignAds;
import com.sh.adsp.campaign.domain.CampaignAdsReader;
import com.sh.adsp.campaign.domain.FirstCampaignAdWeightedPicker;
import com.sh.adsp.core.exception.IllegalStatusException;
import com.sh.adsp.core.ReentrantLockTemplate;
import com.sh.adsp.core.WeightedPicker;
import java.util.List;
import java.util.Set;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ZSetOperations;
import org.springframework.stereotype.Component;

@Component
public class FirstCampaignAdWeightedPickerImpl implements FirstCampaignAdWeightedPicker {
  private final ZSetOperations<String, Long> zSetOperations;
  private final CampaignAdsReader campaignAdsReader;
  private WeightedPicker<CampaignAds> weightedPicker;
  private final ReentrantLockTemplate reentrantLockTemplate = new ReentrantLockTemplate();

  public FirstCampaignAdWeightedPickerImpl(RedisTemplate<String, Long> redisTemplate, CampaignAdsReader campaignAdsReader) {
    this.zSetOperations = redisTemplate.opsForZSet();
    this.campaignAdsReader = campaignAdsReader;
  }

  public CampaignAds pick() {
    return this.reentrantLockTemplate.execute(() -> this.weightedPicker.next());
  }

  protected void refreshWeightedPicker() {
    Set<ZSetOperations.TypedTuple<Long>> tuple = this.zSetOperations.rangeWithScores(RedisKey.CAMPAIGN_ADS, 0L, 0L);
    if (tuple == null) {
      throw new IllegalStatusException("레디스에 광고 데이터가 없습니다.");
    } else {
      List<CampaignAds> campaignAdsList = this.campaignAdsReader.loadByPriority(tuple.iterator().next().getScore().intValue());
      this.reentrantLockTemplate.execute(() -> {
        this.weightedPicker = new WeightedPicker<>();
        this.weightedPicker.addAll(campaignAdsList, CampaignAds::getFirstDisplayWeight);
        return this.weightedPicker;
      });
    }
  }
}
