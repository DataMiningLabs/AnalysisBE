package com.knhu.shtefan.analysis.spam.analysis.dto;

import java.util.Map;

public class WordsContainer {

  private static Map<String, Word> words;

  public static void setWords(Map<String, Word> mapOfWords) {
    words = mapOfWords;
  }

  public static boolean contains(String word) {
    return words.containsKey(word);
  }

  public static Word get(String word) {
    return words.get(word);
  }

}
