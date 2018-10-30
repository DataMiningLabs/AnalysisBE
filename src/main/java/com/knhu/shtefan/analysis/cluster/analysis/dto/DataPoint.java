package com.knhu.shtefan.analysis.cluster.analysis.dto;

/**
 This class represents a candidate for Cluster analysis. A candidate must have
 a name and two independent variables on the basis of which it is to be clustered.
 A Data Point must have two variables and a name. A Vector of  Data Point object
 is fed into the constructor of the JCA class. JCA and DataPoint are the only
 classes which may be available from other packages.
*/

public class DataPoint {

  public double x,y;
  public String objName;
  private Cluster cluster;
  public double euDt;

  public DataPoint(double x, double y, String name) {
    this.x = x;
    this.y = y;
    this.objName = name;
    this.cluster = null;
  }

  void setCluster(Cluster cluster) {
    this.cluster = cluster;
    calcEuclideanDistance();
  }

  void calcEuclideanDistance() {
    euDt = Math.sqrt(Math.pow((x - cluster.getCentroid().cX), 2)
      + Math.pow((y - cluster.getCentroid().cY), 2));
  }

  public double testEuclideanDistance(Centroid c) {
    return Math.sqrt(Math.pow((x - c.cX), 2) + Math.pow((y - c.cY), 2));
  }

}