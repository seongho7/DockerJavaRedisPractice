package com.sh.adsp.campaign.infrastructure.persistence;

import com.sh.adsp.campaign.domain.CampaignAds;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CampaignAdsJpaRepository extends JpaRepository<CampaignAds, Long> {
  List<CampaignAds> findByFirstDisplayPriority(int firstDisplayPriority);
}
