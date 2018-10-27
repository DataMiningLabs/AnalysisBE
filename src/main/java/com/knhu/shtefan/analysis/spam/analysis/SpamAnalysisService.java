package com.knhu.shtefan.analysis.spam.analysis;

import java.util.Map;

public interface SpamAnalysisService {

  Map getPointsByFileName(String fileName);

  Map analyseMessage(String message);

}
