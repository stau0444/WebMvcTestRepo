package com.example.springcalc.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

@Data
@AllArgsConstructor
@RequiredArgsConstructor
public class Res {

    private int result;

    private Body respBody;

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class Body{
        private String resultCode = "OK";
    }
}
