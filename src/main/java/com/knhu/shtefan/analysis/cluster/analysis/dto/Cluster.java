package com.knhu.shtefan.analysis.cluster.analysis.dto;

import java.util.ArrayList;
import java.util.List;

/**
 * This class represents a Cluster in a Cluster Analysis Instance. A Cluster is associated
 * with one and only one JCA Instance. A Cluster is related to more than one DataPoints and
 * one centroid.
*/

public class Cluster {

  private Centroid centroid;
  private List<DataPoint> dataPoints = new ArrayList<>();

  void calcSumOfSquares() {
    double temp = 0;
    for (DataPoint dataPoint : dataPoints) {
      temp = temp + dataPoint.euDt;
    }
  }

  public void add(DataPoint dp) {
    dp.setCluster(this); //initiates a inner call to calcEuclideanDistance() in DP.
    dataPoints.add(dp);
    calcSumOfSquares();
  }

  public DataPoint get(int pos) {
    return dataPoints.get(pos);
  }

  public void remove(DataPoint dp) {
    dataPoints.remove(dp);
    calcSumOfSquares();
  }

  public int size() {
    return dataPoints.size();
  }

  public List<DataPoint> list() {
    return dataPoints;
  }

  public void setCentroid(Centroid c) {
    centroid = c;
  }

  public Centroid getCentroid() {
    return centroid;
  }

}