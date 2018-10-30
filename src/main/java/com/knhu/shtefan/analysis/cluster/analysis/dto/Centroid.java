package com.knhu.shtefan.analysis.cluster.analysis.dto;

/**
 * This class represents the Centroid for a Cluster. The initial centroid is calculated
 * using a equation which divides the sample space for each dimension into equal parts
 * depending upon the value of k.
 */

public class Centroid {

  double cX, cY;
  private Cluster cluster;

  public Centroid(double cx, double cy) {
    this.cX = cx;
    this.cY = cy;
  }

  public void calcCentroid() {
    int numDP = cluster.size();

    double tempX = 0, tempY = 0;
    for (int i = 0; i < numDP; i++) { //caluclating the new Centroid
      tempX = tempX + cluster.get(i).x; //total for x
      tempY = tempY + cluster.get(i).y; //total for y
    }

    this.cX = tempX / numDP;
    this.cY = tempY / numDP;

    //calculating the new Euclidean Distance for each Data Point
    for (int i = 0; i < numDP; i++) {
      cluster.get(i).calcEuclideanDistance();
    }
    //calculate the new Sum of Squares for the Cluster
    cluster.calcSumOfSquares();
  }

  public void setCluster(Cluster c) {
    this.cluster = c;
  }

}