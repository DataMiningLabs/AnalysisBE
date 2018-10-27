package com.knhu.shtefan.analysis.spam.analysis;

import com.knhu.shtefan.analysis.spam.analysis.dto.Word;
import com.knhu.shtefan.analysis.spam.analysis.dto.WordsContainer;
import com.knhu.shtefan.analysis.spam.analysis.utils.WordCleaner;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.ArrayList;
import java.util.Map;
import java.util.HashMap;


@Component
public class MessageAnalyser {

  public Map analyseMessage(String message) {
    String type = MessageType.UNDEFINED.name();

    if (!message.isEmpty()) {
      ArrayList<Word> messageAsList = makeWordList(message);
      type = calculateBayes(messageAsList).name();
    }

    Map<String, Object> response = new HashMap<>();
    response.put("type", type);
    return response;
  }

  private ArrayList<Word> makeWordList(String message){
    ArrayList<Word> wordList = new ArrayList<>();
    for (String word : message.split(" ")){
      word = WordCleaner.clean(word);
      if (!word.isEmpty()) {
        Word w;
        if (WordsContainer.contains(word)) {
          w = WordsContainer.get(word);
        } else {
          w = new Word(word);
          w.setProbOfSpam(0.40f);
        }
        wordList.add(w);
      }
    }
    return wordList;
  }

  /**
   * Applying Bayes rule and calculating probability of ham or spam.
   * @return message type
   */
  private MessageType calculateBayes(List<Word> message){
    float probabilityOfPositiveProduct = 1.0f;
    float probabilityOfNegativeProduct = 1.0f;

    for (Word word : message) {
      probabilityOfPositiveProduct *= word.getProbOfSpam();
      probabilityOfNegativeProduct *= (1.0f - word.getProbOfSpam());
    }

    float probOfSpam = probabilityOfPositiveProduct / (probabilityOfPositiveProduct + probabilityOfNegativeProduct);

    return probOfSpam > 0.6f ? MessageType.SPAM : MessageType.HAM; // 0.9 - default
  }

  private enum MessageType {
    UNDEFINED,
    SPAM,
    HAM
  }

}
