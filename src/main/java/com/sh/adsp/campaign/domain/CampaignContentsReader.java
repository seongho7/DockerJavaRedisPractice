package com.sh.adsp.campaign.domain;

import java.util.List;

public interface CampaignContentsReader {
  List<CampaignContents> loadByIds(List<Long> ids, boolean orderByInput);
}
