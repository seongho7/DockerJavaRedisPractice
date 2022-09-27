package com.sh.adsp.core;

import java.util.HashMap;
import java.util.Map;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class WeightedPickerTest {

  @DisplayName("가중치 별로 배열에서 선택이 되는지 검증")
  @Test
  void weighted_pick_correctly() {
    WeightedPicker<String> weightedPicker = new WeightedPicker();
    weightedPicker.add(1, "일");
    weightedPicker.add(2, "이");
    weightedPicker.add(3, "삼");
    weightedPicker.add(4, "사");
    Map<String, Integer> pickStat = new HashMap();
    int totalRequest = 10000;

    for(int i = 0; i < totalRequest; ++i) {
      String key = weightedPicker.next();
      int count = pickStat.getOrDefault(key, 0);
      pickStat.put(key, count + 1);
    }

    pickStat.forEach((k, v) -> {
      double percent = (double)v / (double)totalRequest * 100.0;
      System.out.println(k + ":" + percent);
      if ("일".equals(k)) {
        Assertions.assertTrue(percent >= 8.5 && percent <= 11.5);
      } else if ("이".equals(k)) {
        Assertions.assertTrue(percent >= 18.5 && percent <= 21.5);
      } else if ("삼".equals(k)) {
        Assertions.assertTrue(percent >= 28.5 && percent <= 31.5);
      } else if ("사".equals(k)) {
        Assertions.assertTrue(percent >= 38.5 && percent <= 41.5);
      }

    });
  }
}
