package com.sh.adsp;

import com.sh.adsp.campaign.domain.CampaignAds;
import com.sh.adsp.campaign.domain.CampaignContents;
import com.sh.adsp.campaign.infrastructure.RedisKey;
import com.sh.adsp.campaign.infrastructure.persistence.CampaignAdsJpaRepository;
import com.sh.adsp.campaign.infrastructure.persistence.CampaignContentsRepository;
import java.util.Iterator;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.core.annotation.Order;
import org.springframework.data.redis.core.RedisOperations;
import org.springframework.data.redis.core.ZSetOperations;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class RedisTestDataLoader {
  private final CampaignAdsJpaRepository campaignAdsJpaRepository;
  private final CampaignContentsRepository campaignContentsRepository;
  private final RedisOperations<String, Long> adRedis;

  @Order(Integer.MIN_VALUE)
  @EventListener({ApplicationReadyEvent.class})
  public void load() {
    this.loadAds();
    this.loadContents();
  }

  private void loadAds() {
    List<CampaignAds> campaignAdsList = this.campaignAdsJpaRepository.findAll();
    ZSetOperations<String, Long> zSet = this.adRedis.opsForZSet();
    Iterator iterator = campaignAdsList.iterator();

    while(iterator.hasNext()) {
      CampaignAds campaignAds = (CampaignAds)iterator.next();
      zSet.add(RedisKey.CAMPAIGN_ADS, campaignAds.getId(), campaignAds.getFirstDisplayPriority());
    }

  }

  private void loadContents() {
    List<CampaignContents> campaignContents = this.campaignContentsRepository.findAll();
    ZSetOperations<String, Long> zSet = this.adRedis.opsForZSet();
    Iterator iterator = campaignContents.iterator();

    while(iterator.hasNext()) {
      CampaignContents campaignContent = (CampaignContents)iterator.next();
      zSet.add(RedisKey.CAMPAIGN_CONTENTS, campaignContent.getId(), campaignContent.getFirstDisplayPriority());
    }
  }
}
