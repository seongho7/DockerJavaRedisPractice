package com.sh.adsp.campaign.infrastructure.persistence;

import com.sh.adsp.campaign.domain.CampaignContents;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CampaignContentsRepository extends JpaRepository<CampaignContents, Long> {
}
