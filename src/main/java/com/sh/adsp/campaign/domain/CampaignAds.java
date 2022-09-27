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
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@Table(
    indexes = {@Index(columnList = "firstDisplayPriority, firstDisplayWeight DESC")}
)
@Entity
public class CampaignAds {
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

  @Builder
  public CampaignAds(String name, String imageUrl, int firstDisplayPriority, int firstDisplayWeight, int frequency, String landingUrl) {
    this.name = name;
    this.imageUrl = imageUrl;
    this.firstDisplayPriority = firstDisplayPriority;
    this.firstDisplayWeight = firstDisplayWeight;
    this.frequency = frequency;
    this.landingUrl = landingUrl;
  }

  @Override
  public String toString() {
    return new StringBuilder().append("CampaignAds{").append("id=").append(id).append(", name='").append(name).append('\'').append(", imageUrl='")
        .append(imageUrl).append('\'').append(", firstDisplayPriority=").append(firstDisplayPriority).append(", firstDisplayWeight=")
        .append(firstDisplayWeight).append(", frequency=").append(frequency).append(", landingUrl='").append(landingUrl).append('\'').append('}')
        .toString();
  }
}
