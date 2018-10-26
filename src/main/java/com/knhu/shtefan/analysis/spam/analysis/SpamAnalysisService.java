package com.knhu.shtefan.analysis.spam.analysis;

import com.knhu.shtefan.analysis.spam.analysis.dto.SortedMessagesCount;

import java.util.Map;

public interface SpamAnalysisService {

  Map getPointsByFileName(String fileName);

}
