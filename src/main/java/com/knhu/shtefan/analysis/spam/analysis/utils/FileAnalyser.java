package com.knhu.shtefan.analysis.spam.analysis.utils;


import com.knhu.shtefan.analysis.spam.analysis.dto.SortedMessages;
import com.knhu.shtefan.analysis.spam.analysis.dto.SortedMessagesCount;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

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
@Service
public class FileAnalyser {

  private static final String FILE_NAME = "english.txt";

  public Map<String, Object> getTopPoints() {
    SortedMessages sortedMessages = sortMessages();
    if (Objects.isNull(sortedMessages)) {
      log.error("SortedMessages is null.");
      throw new NullPointerException("SortedMessages is null.");
    }

    Map<String, Integer> spamWordsFrequency = analyseMessages(sortedMessages.getSpam());
    List topSpam = ConverterToPoint.convertToTop10Points(spamWordsFrequency);

    Map<String, Integer> hamWordsFrequency = analyseMessages(sortedMessages.getHam());
    List topHam = ConverterToPoint.convertToTop10Points(hamWordsFrequency);

    Map<String, Object> response = new HashMap<>();
     response.put("spam", topSpam);
     response.put("ham", topHam);

    return response;
  }

  public SortedMessagesCount getSortedMessagesCount() {
    SortedMessages sortedMessages = sortMessages();
    if (Objects.isNull(sortedMessages)) {
      log.error("SortedMessages is null.");
      throw new NullPointerException("SortedMessages is null.");
    }

    int spamCount = sortedMessages.getSpam().size();
    int hamCount = sortedMessages.getHam().size();

    SortedMessagesCount sortedMessagesCount = new SortedMessagesCount(spamCount, hamCount);

    return sortedMessagesCount;
  }

  /**
   * Read all messages from file and sorted them in list of spam or list of ham messages.
   * Returns null if some exception happens.
   *
   * @return object of SortedMessages where spam and ham messages are in different lists
   */
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

  /**
   * Count each word frequency in all messages, return map of
   * word - frequency.
   *
   * @param messages list of messages
   * @return map of word frequency
   */
  private Map<String, Integer> analyseMessages(List<String> messages) {
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

    return wordsFrequency;
  }

}
