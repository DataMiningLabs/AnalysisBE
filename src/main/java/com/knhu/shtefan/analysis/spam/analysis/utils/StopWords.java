package com.knhu.shtefan.analysis.spam.analysis.utils;

import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

@Log4j2
@Component
public class StopWords {

  private static final String fileName = "stopwords.txt";

  private static final Set<String> stopWords;
  static {
    stopWords = new HashSet<>();
    try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
      String line;
      while ((line = br.readLine()) != null) {
        if (!line.isEmpty()) {
          stopWords.add(line);
        }
      }
      log.info("Stop words are read and saved from '" + fileName + "'.");
    } catch (IOException e) {
      log.warn("Something went wrong while working with file '" + fileName + "'.");
    }
  }

  public static boolean contains(String stopWord) {
    return stopWords.contains(stopWord);
  }

}
