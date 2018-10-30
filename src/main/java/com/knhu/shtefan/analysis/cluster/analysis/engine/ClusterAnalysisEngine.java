package com.knhu.shtefan.analysis.cluster.analysis.engine;

import com.knhu.shtefan.analysis.cluster.analysis.dto.Centroid;
import com.knhu.shtefan.analysis.cluster.analysis.dto.Cluster;
import com.knhu.shtefan.analysis.cluster.analysis.dto.DataPoint;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 This class is the entry point for constructing Cluster Analysis objects.
 Each instance of JCA object is associated with one or more clusters, 
 and a Vector of DataPoint objects. The JCA and DataPoint classes are
 the only classes available from other packages.
*/

public class ClusterAnalysisEngine {
  private List<Cluster> clusters;
  private int iter;
  private List<DataPoint> dataPoints;

  public ClusterAnalysisEngine(int k, int iter, List<DataPoint> dataPoints) {
    clusters = new ArrayList<>(k);
    for (int i = 0; i < k; i++) {
      clusters.add(i, new Cluster());
    }
    this.iter = iter;
    this.dataPoints = dataPoints;
  }

  public void startAnalysis() {
    setInitialCentroids(); //set Starting centroid positions - Start of Step 1
    int n = 0;
    boolean run = true;
    //assign DataPoint to clusters
    while (run) {
      for (Cluster cluster : clusters) {
        cluster.add(dataPoints.get(n));
        n++;
        if (n >= dataPoints.size()) {
          run = false;
          break;
        }
      }
    }

    //recalculate Cluster centroids - Start of Step 2
    for (Cluster cluster : clusters) {
      cluster.getCentroid().calcCentroid();
    }

    for (int i = 0; i < iter; i++) {
      //enter the loop for cluster 1
      for (int j = 0; j < clusters.size(); j++) {
        for (int k = 0; k < clusters.get(j).size(); k++) {

          //pick the first element of the first cluster
          //get the current Euclidean distance
          double tempEuDt = clusters.get(j).get(k).euDt;
          Cluster tempCluster = null;
          boolean matchFoundFlag = false;

          //call testEuclidean distance for all clusters
          for (Cluster cluster : clusters) {
            //if testEuclidean < currentEuclidean then
            if (tempEuDt > clusters.get(j).get(k).testEuclideanDistance(cluster.getCentroid())) {
              tempEuDt = clusters.get(j).get(k).testEuclideanDistance(cluster.getCentroid());
              tempCluster = cluster;
              matchFoundFlag = true;
            }
            //if statement - Check whether the Last EuDt is > Present EuDt

          }
          //for variable 'l' - Looping between different Clusters for matching a Data Point.
          //add DataPoint to the cluster and calcSWCSS

          if (matchFoundFlag) {
            tempCluster.add(clusters.get(j).get(k));
            clusters.get(j).remove(clusters.get(j).get(k));
            for (Cluster cluster : clusters) {
              cluster.getCentroid().calcCentroid();
            }
          }

//if statement - A Data Point is eligible for transfer between Clusters.
        }
        //for variable 'k' - Looping through all Data Points of the current Cluster.
      }//for variable 'j' - Looping through all the Clusters.
    }//for variable 'i' - Number of iterations.
  }

  private void setInitialCentroids() {
    // kn = (round((max-min)/k)*n)+min where n is from 0 to (k-1).
    double cX, cY;
    for (int i = 1; i <= clusters.size(); i++) {
      cX = (((getMaxXValue() - getMinXValue()) / (clusters.size() + 1)) * i) + getMinXValue();
      cY = (((getMaxYValue() - getMinYValue()) / (clusters.size() + 1)) * i) + getMinYValue();
      Centroid c1 = new Centroid(cX, cY);
      clusters.get(i -  1).setCentroid(c1);
      c1.setCluster(clusters.get(i -  1));
    }
  }

  private double getMaxXValue() {
    double temp;
    temp = dataPoints.get(0).x;
    for (DataPoint dp : dataPoints) {
      temp = (dp.x > temp) ? dp.x : temp;
    }
    return temp;
  }

  private double getMinXValue() {
    double temp;
    temp = dataPoints.get(0).x;
    for (DataPoint dp : dataPoints) {
      temp = (dp.x < temp) ? dp.x : temp;
    }
    return temp;
  }

  private double getMaxYValue() {
    double temp;
    temp = dataPoints.get(0).y;
    for (DataPoint dp : dataPoints) {
      temp = (dp.y > temp) ? dp.y : temp;
    }
    return temp;
  }

  private double getMinYValue() {
    double temp;
    temp = dataPoints.get(0).y;
    for (DataPoint dp : dataPoints) {
      temp = (dp.y < temp) ? dp.y : temp;
    }
    return temp;
  }

  public List getClusterOutput() {
    List lists [] = new List[clusters.size()];
    for (int i = 0; i < clusters.size(); i++) {
      lists[i] = clusters.get(i).list();
    }
    return Arrays.asList(lists);
  }

}