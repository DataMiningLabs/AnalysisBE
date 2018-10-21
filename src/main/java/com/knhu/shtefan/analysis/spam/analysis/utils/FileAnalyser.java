package com.knhu.shtefan.analysis.spam.analysis.utils;

import com.knhu.shtefan.analysis.spam.analysis.dto.Point;
import com.knhu.shtefan.analysis.spam.analysis.dto.SortedMessages;
import com.knhu.shtefan.analysis.spam.analysis.dto.SortedMessagesCount;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import java.util.Objects;
import java.util.Collections;
import java.util.Arrays;
import java.util.List;
import java.util.ArrayList;
import java.util.Map;
import java.util.HashMap;

@Log4j2
@Component
public class FileAnalyser {

  public Map<String, Object> getTopPoints(String fileName) {
    SortedMessages sortedMessages = sortMessages(fileName);
    if (Objects.isNull(sortedMessages)) {
      log.error("SortedMessages is null.");
      throw new NullPointerException("SortedMessages is null.");
    }

    Map<String, Object> response = new HashMap<>();
    response.put("spam", analyseMessages(sortedMessages.getSpamList()));
    response.put("ham", analyseMessages(sortedMessages.getHamList()));

    return response;
  }

  public SortedMessagesCount getSortedMessagesCount(String fileName) {
    SortedMessages sortedMessages = sortMessages(fileName);
    if (Objects.isNull(sortedMessages)) {
      log.error("SortedMessages is null.");
      throw new NullPointerException("SortedMessages is null.");
    }

    int spamCount = sortedMessages.getSpamSize();
    int hamCount = sortedMessages.getHamSize();

    return new SortedMessagesCount(spamCount, hamCount);
  }

  private SortedMessages sortMessages(String fileName) {
    SortedMessages sortedMessages = new SortedMessages();
    try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
      String line;
      while ((line = br.readLine()) != null) {
        if (line.endsWith("spam")) {
          sortedMessages.addSpam(line);
        }
        if (line.endsWith("ham")) {
          sortedMessages.addHam(line);
        }
      }
    } catch (IOException e) {
      log.error("Something went wrong while working with file.");
      return null;
    }
    return sortedMessages;
  }

  private List<Point> analyseMessages(StringBuilder messages) {
    List<String> words = Arrays.asList(messages.toString().split(" "));

    Map<String, Integer> uniquesWords = new HashMap<>();
    words.forEach(word -> uniquesWords.merge(word, 1, Integer::sum));

    List<Map.Entry<String, Integer>> list = new ArrayList<>(uniquesWords.entrySet());
    list.sort(Map.Entry.comparingByValue());
    Collections.reverse(list);

    List<Point> points = new ArrayList<>(10);
    for (Map.Entry<String, Integer> item : list.subList(0, TOP_NUMBER)) {
      points.add(new Point(item.getKey(), item.getValue()));
    }

    return points;
  }

  private static final int TOP_NUMBER = 10;

}
