package com.knhu.shtefan.analysis.cluster.analysis.dto;

import lombok.Data;

@Data
public class ColorPoint {

  private double x;
  private double y;
  private String color;

  public ColorPoint(double x, double y, String color) {
    this.x = x;
    this.y = y;
    this.color = color;
  }
}
