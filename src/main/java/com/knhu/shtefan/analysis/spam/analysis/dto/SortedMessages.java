package com.knhu.shtefan.analysis.spam.analysis.dto;

import lombok.Getter;

@Getter
public class SortedMessages {

  private StringBuilder spamList = new StringBuilder();
  private int spamSize;

  private StringBuilder hamList = new StringBuilder();
  private int hamSize;

  public void addSpam(String spam) {
    spam = spam.replaceAll("[,|./;:\"']", "");
    System.out.println(spam);
    spamList.append(spam);
    spamSize += 1;
  }

  public void addHam(String ham) {
    ham = ham.replaceAll("[,|./;:\"']", "");
    hamList.append(ham);
    hamSize += 1;
  }

}
