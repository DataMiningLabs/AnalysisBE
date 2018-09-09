package com.knhu.shtefan.analysis.spam.analysis;

import com.knhu.shtefan.analysis.spam.analysis.dto.SortedMessagesCount;
import com.knhu.shtefan.analysis.spam.analysis.utils.FileAnalyser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class SpamAnalysisServiceImpl implements SpamAnalysisService {

  @Autowired
  private FileAnalyser fileAnalyser;

  @Override
  public Map getPoints() {
    return fileAnalyser.getTopPoints();
  }

  @Override
  public SortedMessagesCount getNumberOfMessages() {
    return fileAnalyser.getSortedMessagesCount();
  }

}
