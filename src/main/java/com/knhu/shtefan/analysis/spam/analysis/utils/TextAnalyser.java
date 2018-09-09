package com.knhu.shtefan.analysis.spam.analysis.utils;


import com.knhu.shtefan.analysis.spam.analysis.dto.Point;
import com.knhu.shtefan.analysis.spam.analysis.dto.SortedMessages;
import lombok.extern.log4j.Log4j2;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import java.util.Objects;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.HashMap;

@Log4j2
public class TextAnalyser {

  public static Map getTopPoints() {
    return analyseFile();
  }

  private static Map<String, Object> analyseFile() {
    SortedMessages sortedMessages = sortMessages();
    if (Objects.isNull(sortedMessages)) {
      throw new NullPointerException("Sorted messages are null.");
    }

    Map<String, Object> response = new HashMap<>();
    response.put("spam", analyseMessages(sortedMessages.getSpam()));
    response.put("ham", analyseMessages(sortedMessages.getHam()));

    return response;
  }


  /**
   * Read all messages from file and sorted them in list of spam or list of ham messages.
   * Returns null if some exception happens.
   *
   * @return object of SortedMessages where spam and ham messages are in different lists
   */
  private static SortedMessages sortMessages() {
    SortedMessages sortedMessages = new SortedMessages();
    try (BufferedReader br = new BufferedReader(new FileReader("english.txt"))) {
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

  private static List<Point> analyseMessages(List<String> messages) {
    Map<String, Integer> wordsFrequency = new TreeMap<>();
    for (String message : messages) {
      message = message.replace("...", "");

      List<String> words = Arrays.asList(message.split(" "));

      words.forEach(word -> {
        if (wordsFrequency.containsKey(word)) {
          int frequency = wordsFrequency.get(word);
          frequency += 1;
          wordsFrequency.put(word, frequency);
        }

        if (!wordsFrequency.containsKey(word)) {
          wordsFrequency.put(word, 1);
        }
      });
    }

    return ConverterToPoint.convertToTop10Points(wordsFrequency);
  }

}
