package com.knhu.shtefan.analysis.rest;

import com.knhu.shtefan.analysis.cluster.analysis.ClusterAnalysisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/rest/cluster/")
public class ClusterAnalysisController {

  @Autowired
  private ClusterAnalysisService clusterAnalysisService;

  @RequestMapping(value = "{numberOfClusters}", method = RequestMethod.GET, consumes = MediaType.APPLICATION_JSON_VALUE)
  public @ResponseBody
  ResponseEntity analyseMessage(@PathVariable("numberOfClusters") Integer numberOfClusters) {
    List response = clusterAnalysisService.analyseCluster(numberOfClusters);
    return new ResponseEntity<>(response, HttpStatus.OK);
  }

}
