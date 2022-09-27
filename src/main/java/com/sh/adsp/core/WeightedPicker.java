package com.sh.adsp.core;

import java.util.List;
import java.util.NavigableMap;
import java.util.TreeMap;
import java.util.concurrent.ThreadLocalRandom;
import java.util.function.Function;

public class WeightedPicker<E> {
  private final NavigableMap<Integer, E> map = new TreeMap();
  private int totalWeight = 0;

  public WeightedPicker() {
  }

  public WeightedPicker<E> add(int weight, E result) {
    if (weight > 0) {
      this.totalWeight += weight;
      this.map.put(this.totalWeight, result);
    }
    return this;
  }

  public WeightedPicker<E> addAll(List<E> elements, Function<E, Integer> getWeight) {
    if (elements.size() >= 1) {
      for (E element : elements) {
        add(getWeight.apply(element), element);
      }
    }
    return this;
  }

  public E next() {
    int v = ThreadLocalRandom.current().nextInt(this.totalWeight);
    return this.map.higherEntry(v).getValue();
  }
}
