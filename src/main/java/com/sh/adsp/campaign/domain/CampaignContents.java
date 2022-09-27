package com.sh.adsp.campaign.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Index;
import javax.persistence.Table;
import lombok.Builder;
import lombok.Getter;

@Getter
@Table(
    indexes = {@Index(columnList = "firstDisplayPriority, firstDisplayWeight")}
)
@Entity
public class CampaignContents {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private long id;
  @Column(nullable = false, length = 64)
  private String name;
  @Column(nullable = false, length = 256)
  private String imageUrl;
  @Column(nullable = false)
  private int firstDisplayPriority;
  @Column(nullable = false)
  private int firstDisplayWeight;
  @Column(nullable = false)
  private int frequency;
  @Column(nullable = false, length = 256)
  private String landingUrl;

  public CampaignContents() {
  }

  @Builder
  public CampaignContents(String name, String imageUrl, int firstDisplayPriority, int firstDisplayWeight, int frequency, String landingUrl) {
    this.name = name;
    this.imageUrl = imageUrl;
    this.firstDisplayPriority = firstDisplayPriority;
    this.firstDisplayWeight = firstDisplayWeight;
    this.frequency = frequency;
    this.landingUrl = landingUrl;
  }
}
