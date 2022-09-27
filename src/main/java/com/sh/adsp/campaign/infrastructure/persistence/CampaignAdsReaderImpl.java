package com.sh.adsp.campaign.infrastructure.persistence;

import com.sh.adsp.campaign.domain.CampaignAds;
import com.sh.adsp.campaign.domain.CampaignAdsReader;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@RequiredArgsConstructor
@Repository
public class CampaignAdsReaderImpl implements CampaignAdsReader {
  private final CampaignAdsJpaRepository jpaRepository;

  public List<CampaignAds> loadByIds(List<Long> ids, boolean orderByInput) {
    List<CampaignAds> campaignAds = this.jpaRepository.findAllById(ids);
    if (!orderByInput) {
      return campaignAds;
    }
    Map<Long, CampaignAds> map = campaignAds.stream().collect(Collectors.toMap(CampaignAds::getId, Function.identity()));
    return ids.stream().filter(map::containsKey).map(map::get).collect(Collectors.toList());
  }

  public List<CampaignAds> loadByPriority(int firstDisplayPriority) {
    return this.jpaRepository.findByFirstDisplayPriority(firstDisplayPriority);
  }
}
