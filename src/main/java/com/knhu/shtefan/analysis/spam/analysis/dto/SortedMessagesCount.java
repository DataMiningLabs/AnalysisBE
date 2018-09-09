package com.knhu.shtefan.analysis.spam.analysis.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class SortedMessagesCount {

  private int spam;
  private int ham;

}
