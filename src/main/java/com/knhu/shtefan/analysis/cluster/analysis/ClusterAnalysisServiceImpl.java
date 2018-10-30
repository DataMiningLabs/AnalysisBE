package com.knhu.shtefan.analysis.cluster.analysis;

import com.knhu.shtefan.analysis.cluster.analysis.dto.ColorPoint;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClusterAnalysisServiceImpl implements ClusterAnalysisService {

  @Autowired
  private Analyser analyser;

  @Override
  public List<ColorPoint> analyseCluster(int numberOfClusters) {
    return analyser.analysePoints(numberOfClusters);
  }
}
