package com.knhu.shtefan.analysis.spam.analysis.utils;

public class WordCleaner {

  /**
   * Receives String and cleans it by following steps:
   * 1) remove all non character symbols ([0-1a-zA-Z])
   * 2) cast string to lower case
   * 3) filter on stop words
   * 4) replace all numbers from word
   *
   * @param word - word to filter
   * @return cleaned word
   */
  public static String clean(String word) {
    word = word.replaceAll("\\W", "");
    word = word.toLowerCase();
    word = StopWords.contains(word) ? "" : word;
    word = word.replaceAll("[0-9]", "");
    return word;
  }

}
