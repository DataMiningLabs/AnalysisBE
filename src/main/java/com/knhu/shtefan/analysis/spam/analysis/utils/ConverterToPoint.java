package com.knhu.shtefan.analysis.spam.analysis.utils;

import com.knhu.shtefan.analysis.spam.analysis.dto.Point;

import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.LinkedHashMap;

public class ConverterToPoint {

  public static List<Point> convertToTop10Points(Map<String, Integer> wordsFrequency) {
    List<Point> points = new ArrayList<>();

    wordsFrequency = sortMapByValue(wordsFrequency);

    List<Map.Entry<String, Integer>> list = new LinkedList<>(wordsFrequency.entrySet());
    for (int i = 0; i < 10; i += 1) {
      String x = list.get(i).getKey();
      Integer y = list.get(i).getValue();

      Point point = new Point(x, y);

      points.add(point);
    }

    return points;
  }

  private static <K, V extends Comparable<? super V>> Map<K, V> sortMapByValue(Map<K, V> map) {
    List<Map.Entry<K, V>> list = new LinkedList<>(map.entrySet());
    Collections.sort(list, new Comparator<Map.Entry<K, V>>() {
      @Override
      public int compare(Map.Entry<K, V> e1, Map.Entry<K, V> e2) {
        return e1.getValue().compareTo(e2.getValue());
      }
    });

    /*
     * Because, next actions wil  fill up new Map, and values would be added to the end.
     * That is mean, that we will take reversed map.
     */
    Collections.reverse(list);

    Map<K, V> result = new LinkedHashMap<>();
    for (Map.Entry<K, V> entry : list) {
      result.put(entry.getKey(), entry.getValue());
    }

    return result;
  }

}
