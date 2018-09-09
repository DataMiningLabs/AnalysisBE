package com.knhu.shtefan.analysis.spam.analysis.dto;

import java.util.ArrayList;
import java.util.List;

public class SortedMessages {

  private List<String> spamList = new ArrayList<>();

  private List<String> hamList = new ArrayList<>();

  public List<String> getSpam() {
    return spamList;
  }

  public void addSpam(String spam) {
    spamList.add(spam);
  }

  public List<String> getHam() {
    return hamList;
  }

  public void addHam(String ham) {
    hamList.add(ham);
  }

}
