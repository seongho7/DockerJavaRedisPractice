package com.sh.adsp.campaign.domain;

import lombok.Getter;
import lombok.ToString;

@ToString
@Getter
public final class CampaignInfo {
  private final Type type;
  private final long id;
  private final String name;
  private final String imageUrl;
  private final int firstDisplayPriority;
  private final int firstDisplayWeight;
  private final int frequency;
  private final String landingUrl;

  public CampaignInfo(CampaignAds ad) {
    this.type = com.sh.adsp.campaign.domain.CampaignInfo.Type.AD;
    this.id = ad.getId();
    this.name = ad.getName();
    this.imageUrl = ad.getImageUrl();
    this.firstDisplayPriority = ad.getFirstDisplayPriority();
    this.firstDisplayWeight = ad.getFirstDisplayWeight();
    this.frequency = ad.getFrequency();
    this.landingUrl = ad.getLandingUrl();
  }

  public CampaignInfo(CampaignContents contents) {
    this.type = com.sh.adsp.campaign.domain.CampaignInfo.Type.CONTENTS;
    this.id = contents.getId();
    this.name = contents.getName();
    this.imageUrl = contents.getImageUrl();
    this.firstDisplayPriority = contents.getFirstDisplayPriority();
    this.firstDisplayWeight = contents.getFirstDisplayWeight();
    this.frequency = contents.getFrequency();
    this.landingUrl = contents.getLandingUrl();
  }

  public boolean isAd() {
    return this.type == Type.AD;
  }

  public boolean isContents() {
    return this.type == Type.CONTENTS;
  }
  public enum Type {
    AD, CONTENTS;
  }
}
