package com.knhu.shtefan.analysis.spam.analysis.dto;

import java.util.Map;

public class WordsContainer {

  private static Map<String, Word> words;

  public static Map<String, Word> getWords() {
    return words;
  }

  public static void setWords(Map<String, Word> mapOfWords) {
    words = mapOfWords;
  }

}
