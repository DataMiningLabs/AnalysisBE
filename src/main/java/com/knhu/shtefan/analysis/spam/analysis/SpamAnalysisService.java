package com.knhu.shtefan.analysis.spam.analysis;

import com.knhu.shtefan.analysis.spam.analysis.utils.TextAnalyser;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class SpamAnalysisService {

  public Map getPoints() {
    return TextAnalyser.getTopPoints();
  }

}
