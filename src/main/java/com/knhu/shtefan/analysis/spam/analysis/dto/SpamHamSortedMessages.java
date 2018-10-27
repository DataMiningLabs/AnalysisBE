package com.knhu.shtefan.analysis.spam.analysis.dto;

import com.knhu.shtefan.analysis.spam.analysis.utils.WordCleaner;

import java.util.HashMap;
import java.util.Map;

public class SpamHamSortedMessages {

  public Map<String, Word> words;
  public int totalSpamCount;
  public int totalHamCount;

  public SpamHamSortedMessages() {
    words = new HashMap<>();
    totalSpamCount = 0;
    totalHamCount = 0;
  }

  public void incTotalSpamCount() {
    totalSpamCount += 1;
  }

  public void incTotalHamCount() {
    totalHamCount += 1;
  }

  public void addNewWord(String word, String type) {
    word = WordCleaner.clean(word);
    if (word.isEmpty()) { return; }

    Word w;
    if (words.containsKey(word)) {
      w = words.get(word);
    } else {
      w = new Word(word);
    }

    if(type.equals("ham")){
      incTotalHamCount();
      w.countHam();
    } else if(type.equals("spam")) {
      incTotalSpamCount();
      w.countSpam();
    }
    words.put(word, w);
  }

}
