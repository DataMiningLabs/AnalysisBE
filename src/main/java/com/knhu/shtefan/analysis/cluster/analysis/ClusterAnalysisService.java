package com.knhu.shtefan.analysis.cluster.analysis;

import com.knhu.shtefan.analysis.cluster.analysis.dto.ColorPoint;

import java.util.List;

public interface ClusterAnalysisService {

  List<ColorPoint> analyseCluster(int numberOfClusters);

}
