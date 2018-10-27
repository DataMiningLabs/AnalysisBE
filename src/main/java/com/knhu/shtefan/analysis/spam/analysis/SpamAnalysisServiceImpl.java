package com.knhu.shtefan.analysis.spam.analysis;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class SpamAnalysisServiceImpl implements SpamAnalysisService {

  @Autowired
  private FileAnalyser fileAnalyser;

  @Autowired
  private MessageAnalyser messageAnalyser;

  @Override
  public Map getPointsByFileName(String fileName) {
    return fileAnalyser.getTopPoints(fileName);
  }

  @Override
  public Map analyseMessage(String message) {
    return messageAnalyser.analyseMessage(message);
  }

}
