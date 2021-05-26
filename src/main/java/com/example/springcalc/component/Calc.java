package com.example.springcalc.component;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class Calc {

    private final ICalc iCalc;

    public int sum(int x , int y ){
        this.iCalc.init();
        return this.iCalc.sum(x,y);
    }

    public int minus(int x , int y){
        this.iCalc.init();
        return this.iCalc.minus(x,y);
    }
}
