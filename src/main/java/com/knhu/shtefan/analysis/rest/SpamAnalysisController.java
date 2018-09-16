package com.knhu.shtefan.analysis.rest;

import com.knhu.shtefan.analysis.spam.analysis.SpamAnalysisService;
import com.knhu.shtefan.analysis.spam.analysis.dto.SortedMessagesCount;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping(value = "/rest/spam/")
public class SpamAnalysisController {

  @Autowired
  private SpamAnalysisService spamAnalysisService;

  @RequestMapping(value = "_points/{fileName}", method = RequestMethod.GET)
  public @ResponseBody ResponseEntity getTopPoints(@PathVariable String fileName) {
    Map response = spamAnalysisService.getPointsByFileName(fileName);
    return new ResponseEntity<>(response, HttpStatus.OK);
  }

  @RequestMapping(value = "_count/{fileName}", method = RequestMethod.GET)
  public @ResponseBody SortedMessagesCount getNumberOfMessages(@PathVariable String fileName) {
    return spamAnalysisService.getNumberOfMessages(fileName);
  }

}
