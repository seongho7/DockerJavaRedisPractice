package com.sh.adsp.campaign.infrastructure.persistence;

import com.sh.adsp.campaign.domain.CampaignContents;
import com.sh.adsp.campaign.domain.CampaignContentsReader;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@RequiredArgsConstructor
@Repository
public class CampaignContentsReaderImpl implements CampaignContentsReader {
  private final CampaignContentsRepository jpaRepository;

  public List<CampaignContents> loadByIds(List<Long> ids, boolean orderByInput) {
    List<CampaignContents> campaignContents = this.jpaRepository.findAllById(ids);
    if (!orderByInput) {
      return campaignContents;
    }
    Map<Long, CampaignContents> map = campaignContents.stream().collect(Collectors.toMap(CampaignContents::getId, Function.identity()));
    return ids.stream().filter(map::containsKey).map(map::get).collect(Collectors.toList());
  }
}
