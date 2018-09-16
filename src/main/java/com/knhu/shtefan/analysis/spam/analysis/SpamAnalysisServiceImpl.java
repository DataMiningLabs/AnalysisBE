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
  public Map getPointsByFileName(String fileName) {
    return fileAnalyser.getTopPoints(fileName);
  }

  @Override
  public SortedMessagesCount getNumberOfMessages(String fileName) {
    return fileAnalyser.getSortedMessagesCount(fileName);
  }

}
