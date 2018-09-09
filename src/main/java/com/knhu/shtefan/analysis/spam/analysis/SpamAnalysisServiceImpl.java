package com.knhu.shtefan.analysis.spam.analysis;

import com.knhu.shtefan.analysis.spam.analysis.utils.FileAnalyser;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class SpamAnalysisServiceImpl implements SpamAnalysisService {
  public Map getPoints() {
    return FileAnalyser.getTopPoints();
  }

}
