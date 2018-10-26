package com.knhu.shtefan.analysis.spam.analysis.dto;

import com.knhu.shtefan.analysis.Word;
import com.knhu.shtefan.analysis.spam.analysis.utils.StopWords;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

public class NeuralNetwork implements Serializable {

  public Map<String, Word> words;
  public int totalSpamCount;
  public int totalHamCount;

  public NeuralNetwork() {
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
    word = clearWord(word);
    if (word.isEmpty()) {
      return;
    }

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

  private String clearWord(String word) {
    word = word.replaceAll("\\W", "");
    word = word.toLowerCase();
    word = StopWords.contains(word) ? "" : word;
    for (int i = 0; i < 10; i++) {
      if (word.contains(i + "")) {
        word = "";
        break;
      }
    }
    return word;
  }

}
