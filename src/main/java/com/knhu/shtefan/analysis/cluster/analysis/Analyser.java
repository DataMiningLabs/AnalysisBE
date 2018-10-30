package com.knhu.shtefan.analysis.cluster.analysis;

import com.knhu.shtefan.analysis.cluster.analysis.dto.Colors;
import com.knhu.shtefan.analysis.cluster.analysis.dto.DataPoint;
import com.knhu.shtefan.analysis.cluster.analysis.engine.ClusterAnalysisEngine;
import com.knhu.shtefan.analysis.cluster.analysis.dto.ColorPoint;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;


@Component
public class Analyser {

  private static final int NUMBER_OF_POINTS = 10000;

  List<ColorPoint> analysePoints(int clusterNumber) {
    List<DataPoint> dataPoints = fillDataPoints();

    ClusterAnalysisEngine clusterAnalysisEngine = new ClusterAnalysisEngine(clusterNumber, 1000, dataPoints);
    clusterAnalysisEngine.startAnalysis();

    List<ColorPoint> points = new ArrayList<>();
    List<List<DataPoint>> clustersList = clusterAnalysisEngine.getClusterOutput();
    for (int i = 0; i < clustersList.size(); i++){
      List<DataPoint> cluster = clustersList.get(i);
      for (DataPoint dataPoint : cluster) {
        points.add(new ColorPoint(dataPoint.x, dataPoint.y, Colors.getColor(i)));
      }
    }

    return points;
  }

  private List<DataPoint> fillDataPoints() {
    List<DataPoint> dataPoints = new ArrayList<>();
    for (int i = 0; i < NUMBER_OF_POINTS; i++) {
      dataPoints.add(new DataPoint(Math.random() * 100,Math.random() * 100, "dataPoint"+i));
    }
    return dataPoints;
  }

}
