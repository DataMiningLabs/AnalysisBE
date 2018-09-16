package com.knhu.shtefan.analysis.spam.analysis.utils;

import com.knhu.shtefan.analysis.spam.analysis.dto.Point;
import com.knhu.shtefan.analysis.spam.analysis.dto.SortedMessages;
import com.knhu.shtefan.analysis.spam.analysis.dto.SortedMessagesCount;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import java.util.List;
import java.util.Map;
import java.util.HashMap;

import java.util.Objects;
import java.util.Collections;
import java.util.Arrays;

import java.util.stream.Collectors;

@Log4j2
@Component
public class FileAnalyser {

  public Map<String, Object> getTopPoints() {
    SortedMessages sortedMessages = sortMessages();
    if (Objects.isNull(sortedMessages)) {
      log.error("SortedMessages is null.");
      throw new NullPointerException("SortedMessages is null.");
    }

    Map<String, Object> response = new HashMap<>();
    response.put("spam", analyseMessages(sortedMessages.getSpamList()));
    response.put("ham", analyseMessages(sortedMessages.getHamList()));

    return response;
  }

  public SortedMessagesCount getSortedMessagesCount() {
    SortedMessages sortedMessages = sortMessages();
    if (Objects.isNull(sortedMessages)) {
      log.error("SortedMessages is null.");
      throw new NullPointerException("SortedMessages is null.");
    }

    int spamCount = sortedMessages.getSpamSize();
    int hamCount = sortedMessages.getHamSize();

    return new SortedMessagesCount(spamCount, hamCount);
  }

  private SortedMessages sortMessages() {
    SortedMessages sortedMessages = new SortedMessages();
    try (BufferedReader br = new BufferedReader(new FileReader(FILE_NAME))) {
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
    return words
      .stream().collect(
        Collectors.toMap(
          word -> word,
          word -> Collections.frequency(words, word),
          (word1, word2) -> word1
        )
      )
      .entrySet().stream()
      .sorted(Map.Entry.<String, Integer>comparingByValue().reversed())
      .limit(10)
      .map(item -> new Point(item.getKey(), item.getValue()))
      .collect(Collectors.toList());
  }

  private static final String FILE_NAME = "english.txt";
  private static final String FILE_NAME_BIG = "english_big.txt";

}
