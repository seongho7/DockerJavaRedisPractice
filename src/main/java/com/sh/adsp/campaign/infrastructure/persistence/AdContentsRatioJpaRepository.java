package com.sh.adsp.campaign.infrastructure.persistence;

import com.sh.adsp.campaign.domain.AdContentsRatio;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AdContentsRatioJpaRepository extends JpaRepository<AdContentsRatio, Integer> {
  Optional<AdContentsRatio> findByType(AdContentsRatio.Type type);
}
