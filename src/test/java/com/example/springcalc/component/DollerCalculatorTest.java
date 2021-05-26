package com.example.springcalc.component;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;

import static org.junit.jupiter.api.Assertions.*;

// 모든 빈이 올라간 상태에서 테스트를 한다.
@SpringBootTest
class DollerCalculatorTest {


    //mock은 모조품 말하고 ,

    //mocking 처리란 모조품을 만들어 테스트에 사용하는 것을 말한다.
    //mocking 처리를 할 객체들은 @MockBean으로 등록해 줘야한다.
    @MockBean
    private MarketApi marketApi;

    @Autowired
    private DollerCalculator dollerCalculator;

    @Test
    public void dollarCalcTest(){

        Mockito.when(marketApi.connect()).thenReturn(3000);
        dollerCalculator.init();

        int sum = dollerCalculator.sum(10,10);

        Assertions.assertEquals(60000,sum);

    }

}