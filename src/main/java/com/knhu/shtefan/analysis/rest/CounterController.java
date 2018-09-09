package com.knhu.shtefan.analysis.rest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping(value = "/rest/counter")
public class CounterController {


  @RequestMapping(method = RequestMethod.GET)
  public ResponseEntity getCount() {
    Map<String, String> response = new HashMap<>();
    response.put("counter", "12");
    return new ResponseEntity<>(response, HttpStatus.OK);
  }

}
