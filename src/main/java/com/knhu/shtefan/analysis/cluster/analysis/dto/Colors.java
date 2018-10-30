package com.knhu.shtefan.analysis.cluster.analysis.dto;

import java.util.HashMap;
import java.util.Map;

public class Colors {

  private static final String DEFAULT_COLOR = "#808080";
  private static final Map<Integer, String> COLORS;
  static {
    COLORS = new HashMap<>();
    COLORS.put(0, "#FF0000");
    COLORS.put(1, "#7FFF00");
    COLORS.put(2, "#4682FF");
    COLORS.put(3, "#FFFF00");
    COLORS.put(4, "#FF00FF");
    COLORS.put(5, "#8B4513");
    COLORS.put(6, "#00FA9A");
    COLORS.put(7, "#000080");
    COLORS.put(8, "#000000");
    COLORS.put(9, "#008080");
    COLORS.put(10, "#800080");
    COLORS.put(11, "#DEB887");
    COLORS.put(12, "#b74602");
    COLORS.put(13, "#11d9ff");
    COLORS.put(14, "#708090");
    COLORS.put(15, "#FF8C00");
  }

  public static String getColor(int number) {
    return COLORS.getOrDefault(number, DEFAULT_COLOR);
  }

}
