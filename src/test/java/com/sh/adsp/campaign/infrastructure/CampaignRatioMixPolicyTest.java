package com.sh.adsp.campaign.infrastructure;

import com.sh.adsp.campaign.domain.AdContentsRatio;
import com.sh.adsp.campaign.domain.AdContentsRatio.Type;
import com.sh.adsp.campaign.domain.CampaignAds;
import com.sh.adsp.campaign.domain.CampaignContents;
import com.sh.adsp.campaign.domain.CampaignInfo;
import com.sh.adsp.campaign.domain.UserId;
import java.time.LocalDateTime;
import java.util.List;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;

@SpringBootTest
class CampaignRatioMixPolicyTest {
  @Autowired
  private CampaignRatioMixPolicy campaignRatioMixPolicy;
  private static final long testUserId = 99999999L;
  @Autowired
  private RedisTemplate<String, Long> adRedisTemplate;


  @DisplayName("""
  컨텐츠와 광고를 섞어서 보여줍니다.
  AdContentsRatio 설정된 값을 통해 첫 번째 캠페인을 결정합니다.
  예를 들어, “3:1” 일 경우, 요청에 대한 응답으로 광고 3번마다 컨텐츠가 1번씩 첫 번째에 위치 해야 합니다. 나머지 캠페인은 랜덤.""")
  @ParameterizedTest
  @CsvSource({"3, 1", "4, 1", "5, 2"})
  void mix_correctly(int ad, int content) {
    var ads = List.of(CampaignAds.builder().name("name").build(), CampaignAds.builder().name("name").build());
    var contentsList = List.of(CampaignContents.builder().name("name").build(), CampaignContents.builder().name("name").build());
    for (int i = 0; i < ad+content; i++) {
      var adContentsRatio = new AdContentsRatio(Type.FIRST, ad, content, LocalDateTime.now());
      campaignRatioMixPolicy.refreshAdContentsRatio(adContentsRatio);
      List<CampaignInfo> campaignInfoList = campaignRatioMixPolicy.mix(new UserId(testUserId), ads, contentsList);
      if(i<ad) {
        Assertions.assertTrue(campaignInfoList.get(0).isAd());
      } else {
        Assertions.assertTrue(campaignInfoList.get(0).isContents());
      }
    }
  }

  @AfterEach
  @BeforeEach
  void beforeEach() {
    adRedisTemplate.opsForHash().delete(RedisKey.USER_REQUEST_CYCLE, testUserId);
  }

}