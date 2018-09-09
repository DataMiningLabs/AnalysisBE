package com.knhu.shtefan.analysis.rest;

import com.knhu.shtefan.analysis.spam.analysis.SpamAnalysisService;
import com.knhu.shtefan.analysis.spam.analysis.dto.SortedMessagesCount;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping(value = "/rest/spam/")
public class SpamAnalysisController {

  @Autowired
  private SpamAnalysisService spamAnalysisService;

  @RequestMapping(value = "_points", method = RequestMethod.GET)
  public @ResponseBody ResponseEntity getTopPoints() {
    Map response = spamAnalysisService.getPoints();
    return new ResponseEntity<>(response, HttpStatus.OK);
  }

  @RequestMapping(value = "_count", method = RequestMethod.GET)
  public @ResponseBody SortedMessagesCount getNumberOfMessages() {
    return spamAnalysisService.getNumberOfMessages();
  }

}
