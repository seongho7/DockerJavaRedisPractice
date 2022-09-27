package com.sh.adsp.campaign.presentation.rest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.fasterxml.jackson.core.type.TypeReference;
import com.sh.adsp.AbstractIntegrationTests;
import com.sh.adsp.core.response.CommonResponse;
import com.sh.adsp.core.response.CommonResponse.Result;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.springframework.http.MediaType;

class AdQueryApiTest extends AbstractIntegrationTests {

  @DisplayName("광고를 무작위로 조회한다.")
  @ParameterizedTest
  @CsvSource({"10", "15"})
  void get_ad_random_correctly(int limit) throws Exception {
    final var actions = mockMvc.perform(get(CampaignApiUrl.AD_QUERY)
        .param("sort", "random")
        .param("limit", String.valueOf(limit))
        .accept(MediaType.APPLICATION_JSON)
        .contentType(MediaType.APPLICATION_JSON)
    );

    actions
        .andDo(print())
        .andExpect(status().isOk())
        .andDo(result -> {
          var res = objectMapper.readValue(result.getResponse().getContentAsString(),
              new TypeReference<CommonResponse<List<AdQueryRes>>>() {});
          assertEquals(Result.SUCCESS, res.getResult());
          assertNotNull(res.getData());
          assertEquals(limit, res.getData().size());
        });
  }

  @DisplayName("limit파라미터가 없으면 default는 10이다.")
  @Test
  void get_ad_limit_default_check() throws Exception {
    final var actions = mockMvc.perform(get(CampaignApiUrl.AD_QUERY)
        .param("sort", "random")
        .accept(MediaType.APPLICATION_JSON)
        .contentType(MediaType.APPLICATION_JSON)
    );

    actions
        .andDo(print())
        .andExpect(status().isOk())
        .andDo(result -> {
          var res = objectMapper.readValue(result.getResponse().getContentAsString(),
              new TypeReference<CommonResponse<List<AdQueryRes>>>() {});
          assertEquals(Result.SUCCESS, res.getResult());
          assertNotNull(res.getData());
          assertEquals(10, res.getData().size());
        });
  }


  @DisplayName("첫 번째 광고는 firstDisplayPriority와 firstDisplayWeight에 따라 결정되며, 나머지 광고들은 랜덤으로 배치된다.")
  @ParameterizedTest
  @CsvSource({"10", "11", "12", "13", "14"})
  void get_ad_priority_correctly(int limit) throws Exception {
    final var actions = mockMvc.perform(get(CampaignApiUrl.AD_QUERY)
        .param("sort", "priority")
        .param("limit", String.valueOf(limit))
        .accept(MediaType.APPLICATION_JSON)
        .contentType(MediaType.APPLICATION_JSON)
    );

    actions
        .andDo(print())
        .andExpect(status().isOk())
        .andDo(result -> {
          var res = objectMapper.readValue(result.getResponse().getContentAsString(),
              new TypeReference<CommonResponse<List<AdQueryRes>>>() {});
          assertEquals(Result.SUCCESS, res.getResult());
          assertNotNull(res.getData());
          assertEquals(limit, res.getData().size());
          assertTrue(List.of(3L,6L,9L).contains(res.getData().get(0).id()));
        });
  }
}