package com.sh.adsp.campaign.infrastructure.persistence;

import com.sh.adsp.campaign.domain.AdContentsRatio;
import com.sh.adsp.campaign.domain.AdContentsRatioReader;
import com.sh.adsp.core.exception.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@RequiredArgsConstructor
@Repository
public class AdContentsRatioReaderImpl implements AdContentsRatioReader {
  private final AdContentsRatioJpaRepository jpaRepository;

  public AdContentsRatio load(AdContentsRatio.Type type) {
    return jpaRepository.findByType(type).orElseThrow(EntityNotFoundException::new);
  }
}
