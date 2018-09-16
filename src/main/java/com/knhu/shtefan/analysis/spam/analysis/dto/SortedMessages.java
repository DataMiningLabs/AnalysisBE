package com.knhu.shtefan.analysis.spam.analysis.dto;

import lombok.Getter;

@Getter
public class SortedMessages {

  public void addSpam(String spam) {
    spam = spam
      .replaceAll(SPAM, SPACE)
      .replaceAll(SYMBOLS, NOTHING)
      .replaceAll(MULTIPLE_SPACES, SPACE)
      .toLowerCase();
    spamList.append(spam);
    spamSize += 1;
  }

  public void addHam(String ham) {
    ham = ham
      .replaceAll(HAM, SPACE)
      .replaceAll(SYMBOLS, NOTHING)
      .replaceAll(MULTIPLE_SPACES, SPACE)
      .toLowerCase();
    hamList.append(ham);
    hamSize += 1;
  }

  private StringBuilder spamList = new StringBuilder();
  private int spamSize;

  private StringBuilder hamList = new StringBuilder();
  private int hamSize;

  private static final String HAM = "ham";
  private static final String MULTIPLE_SPACES = "\\s{2,4}";
  private static final String NOTHING = "";
  private static final String SPACE = " ";
  private static final String SPAM = "spam";
  private static final String SYMBOLS = "[-+.^:,!?;\"()<>=]";

}
