package com.knhu.shtefan.analysis.rest;

import com.knhu.shtefan.analysis.spam.analysis.SpamAnalysisService;
import com.knhu.shtefan.analysis.spam.analysis.dto.MessageDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

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

  @RequestMapping(value = "analyse", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
  public @ResponseBody ResponseEntity analyseMessage(@RequestBody MessageDto messageDto) {
    String message = messageDto.getMessage();
    Map response = spamAnalysisService.analyseMessage(message);
    return new ResponseEntity<>(response, HttpStatus.OK);
  }

}
