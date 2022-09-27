package com.sh.adsp.campaign.presentation.rest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.fasterxml.jackson.core.type.TypeReference;
import com.sh.adsp.AbstractIntegrationTests;
import com.sh.adsp.core.response.CommonResponse;
import com.sh.adsp.core.response.CommonResponse.Result;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;

class FirstAdContentsRatioQueryApiTest extends AbstractIntegrationTests {

  @DisplayName("광고와 컨텐츠의 노출 비율을 조회합니다.")
  @Test
  void get_contents_ratio_correctly() throws Exception {
    final var actions = mockMvc.perform(get(CampaignApiUrl.CONTENTS_RATIO_QUERY)
        .accept(MediaType.APPLICATION_JSON)
        .contentType(MediaType.APPLICATION_JSON)
    );

    actions
        .andDo(print())
        .andExpect(status().isOk())
        .andDo(result -> {
          var res = objectMapper.readValue(result.getResponse().getContentAsString(), new TypeReference<CommonResponse<FirstAdContentsRatioQueryRes>>() {});
          assertEquals(Result.SUCCESS, res.getResult());
          assertNotNull(res.getData());
        });
  }
}