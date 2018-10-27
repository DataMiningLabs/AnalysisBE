package com.knhu.shtefan.analysis.spam.analysis;

import com.knhu.shtefan.analysis.spam.analysis.dto.SpamHamSortedMessages;
import com.knhu.shtefan.analysis.spam.analysis.dto.Point;
import com.knhu.shtefan.analysis.spam.analysis.dto.WordsContainer;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Component;

import java.io.FileReader;
import java.io.BufferedReader;
import java.io.IOException;

import java.util.Collections;
import java.util.List;
import java.util.ArrayList;
import java.util.Map;
import java.util.HashMap;

@Log4j2
@Component
public class FileAnalyser {

  private SpamHamSortedMessages spamHamSortedMessages;

  private int spamMessages = 0;
  private int hamMessages = 0;

  private static final int TOP_NUMBER = 10;

  public Map<String, Object> getTopPoints(String fileName) {
    reset();
    getMessagesFromFile(fileName);

    for (String key : spamHamSortedMessages.words.keySet()) {
      spamHamSortedMessages.words.get(key).calculateProbability(spamHamSortedMessages.totalSpamCount, spamHamSortedMessages.totalHamCount);
    }

    WordsContainer.setWords(spamHamSortedMessages.words);

    Map<String, Object> response = new HashMap<>();
    response.put("spam", analyseMessages("spam"));
    response.put("ham", analyseMessages("ham"));
    response.put("spamCount", spamMessages);
    response.put("hamCount", hamMessages);

    return response;
  }

  private void reset() {
    spamMessages = 0;
    hamMessages = 0;
    spamHamSortedMessages = new SpamHamSortedMessages();
  }

  private void getMessagesFromFile(String fileName) {
    try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
      String line;
      while ((line = br.readLine()) != null) {
        String type = "";
        if (line.endsWith("spam")) {
          line = line.replaceAll("spam", "");
          type = "spam";
          spamMessages += 1;
        }
        if (line.endsWith("ham")) {
          line = line.replaceAll("ham", "");
          type = "ham";
          hamMessages += 1;
        }
        for (String word : line.split(" ")) {
          spamHamSortedMessages.addNewWord(word, type);
        }
      }
    } catch (IOException e) {
      log.error("Something went wrong while working with file.");
    }
  }

  private List<Point> analyseMessages(String type) {
    Map<String, Integer> uniquesWords = new HashMap<>();
    spamHamSortedMessages.words.forEach((k, v) -> {
      int count = -1;
      if (type.equals("spam")) {
        count = v.getSpamCount();
      } else if (type.equals("ham")) {
        count = v.getHamCount();
      }
      uniquesWords.put(k, count);
    });

    List<Map.Entry<String, Integer>> list = new ArrayList<>(uniquesWords.entrySet());
    list.sort(Map.Entry.comparingByValue());
    Collections.reverse(list);

    List<Point> points = new ArrayList<>(10);
    for (Map.Entry<String, Integer> item : list.subList(0, TOP_NUMBER)) {
      points.add(new Point(item.getKey(), item.getValue()));
    }

    return points;
  }

}
