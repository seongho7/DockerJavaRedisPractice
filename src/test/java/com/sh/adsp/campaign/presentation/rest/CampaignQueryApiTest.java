package com.sh.adsp.campaign.presentation.rest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.fasterxml.jackson.core.type.TypeReference;
import com.sh.adsp.AbstractIntegrationTests;
import com.sh.adsp.campaign.domain.CampaignInfo.Type;
import com.sh.adsp.campaign.infrastructure.RedisKey;
import com.sh.adsp.core.response.CommonResponse;
import com.sh.adsp.core.response.CommonResponse.Result;
import java.util.List;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.http.MediaType;


class CampaignQueryApiTest extends AbstractIntegrationTests {
  private static final long testUserId = 99999999L;
  @Autowired
  private RedisTemplate<String, Long> adRedisTemplate;

  /**
   * 1,2,3째는 요청의 1번째는 광고, 4번째 요청의 1번째는 컨텐츠
   * @throws Exception
   */
  @DisplayName("컨텐츠와 광고를 정상 조회한다.")
  @Test
  void get_ad_contents_correctly() throws Exception {
    int adLimit = 3;
    int contentsLimit = 4;

    for (int i = 1; i < 5; i++) {
      final var actions = mockMvc.perform(get(CampaignApiUrl.CAMPAIGN_QUERY)
          .param("sort", "mixed")
          .param("userId", String.valueOf(testUserId))
          .param("adLimit", String.valueOf(adLimit))
          .param("contentsLimit", String.valueOf(contentsLimit))
          .accept(MediaType.APPLICATION_JSON)
          .contentType(MediaType.APPLICATION_JSON)
      );

      int reqCount = i;
      actions
          .andDo(print())
          .andExpect(status().isOk())
          .andDo(result -> {
            var res = objectMapper.readValue(result.getResponse().getContentAsString(),
                new TypeReference<CommonResponse<List<CampaignQueryRes>>>() {});
            assertEquals(Result.SUCCESS, res.getResult());
            assertNotNull(res.getData());
            assertEquals(adLimit + contentsLimit, res.getData().size());
            assertEquals(adLimit, res.getData().stream().filter(it->it.type() == Type.AD).count());
            assertEquals(contentsLimit, res.getData().stream().filter(it->it.type() == Type.CONTENTS).count());
            if(reqCount == 1 || reqCount == 2 || reqCount == 3) {
              assertEquals(Type.AD, res.getData().get(0).type()) ;
            } else if(reqCount == 4) {
              assertEquals(Type.CONTENTS, res.getData().get(0).type()) ;
            }
          });
    }
  }


  @DisplayName("adLimit, contentsLimit 파라미터는 값이 없으면 default가 3이다.")
  @Test
  void get_ad_contents_default_parameter_check() throws Exception {
    int adLimit = 3;
    int contentsLimit = 3;
    final var actions = mockMvc.perform(get(CampaignApiUrl.CAMPAIGN_QUERY)
        .param("sort", "mixed")
        .param("userId", String.valueOf(testUserId))
        .accept(MediaType.APPLICATION_JSON)
        .contentType(MediaType.APPLICATION_JSON)
    );

    actions
        .andDo(print())
        .andExpect(status().isOk())
        .andDo(result -> {
          var res = objectMapper.readValue(result.getResponse().getContentAsString(),
              new TypeReference<CommonResponse<List<CampaignQueryRes>>>() {});
          assertEquals(Result.SUCCESS, res.getResult());
          assertNotNull(res.getData());
          assertEquals(adLimit + contentsLimit, res.getData().size());
          assertEquals(adLimit, res.getData().stream().filter(it->it.type() == Type.AD).count());
          assertEquals(contentsLimit, res.getData().stream().filter(it->it.type() == Type.CONTENTS).count());
        });
  }

  @AfterEach
  @BeforeEach
  void beforeEach() {
    adRedisTemplate.opsForHash().delete(RedisKey.USER_REQUEST_CYCLE, testUserId);
  }
}