package com.example.springcalc.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
@AllArgsConstructor
public class Req {
   private int x;
   private int y;
}
