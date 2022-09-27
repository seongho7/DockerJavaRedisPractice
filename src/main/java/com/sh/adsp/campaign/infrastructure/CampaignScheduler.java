package com.sh.adsp.campaign.infrastructure;

import com.sh.adsp.campaign.domain.AdContentsRatioReader;
import com.sh.adsp.campaign.domain.AdContentsRatio.Type;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class CampaignScheduler {
  private final FirstCampaignAdWeightedPickerImpl firstCampaignAdWeightedPicker;
  private final CampaignRatioMixPolicy campaignRatioMixPolicy;
  private final AdContentsRatioReader adContentsRatioReader;

  @EventListener({ApplicationReadyEvent.class})
  @Scheduled(cron = "0 15 0 ? * *")
  public void executeDaily() {
    firstCampaignAdWeightedPicker.refreshWeightedPicker();
    campaignRatioMixPolicy.refreshAdContentsRatio(this.adContentsRatioReader.load(Type.FIRST));
  }
}
