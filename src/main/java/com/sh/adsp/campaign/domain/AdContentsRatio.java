package com.sh.adsp.campaign.domain;

import java.time.LocalDateTime;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Index;
import javax.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@Table(
    name = "campaign_ad_contents_ratio",
    indexes = {@Index(columnList = "type", unique = true)}
)
@Entity
public class AdContentsRatio {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private long id;
  @Enumerated(EnumType.STRING)
  @Column(length = 6)
  private Type type;
  private int ad;
  private int contents;
  private LocalDateTime createdAt;
  private LocalDateTime updatedAt;

  public int sumOfAdAndContent() {
    return this.ad + this.contents;
  }

  public boolean isAdTurn(int reqCycleValue) {
    return reqCycleValue <= this.ad && 0 < reqCycleValue;
  }

  public boolean isContentsTurn(int reqCycleValue) {
    return !this.isAdTurn(reqCycleValue);
  }

  public int nextCycleValue(Integer reqCycleValue) {
    return reqCycleValue != null && this.sumOfAdAndContent() > reqCycleValue ? reqCycleValue + 1 : 1;
  }

  public AdContentsRatio(Type type, int ad, int contents, LocalDateTime createdAt) {
    this.type = type;
    this.ad = ad;
    this.contents = contents;
    this.createdAt = createdAt;
  }

  @Override
  public String toString() {
    return new StringBuilder().append("AdContentsRatio{").append("id=").append(id).append(", type=").append(type).append(", ad=").append(ad)
        .append(", contents=").append(contents).append(", createdAt=").append(createdAt).append(", updatedAt=").append(updatedAt).append('}')
        .toString();
  }

  public  enum Type {
    FIRST;
  }
}
