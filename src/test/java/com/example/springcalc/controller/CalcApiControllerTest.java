package com.example.springcalc.controller;

import com.example.springcalc.component.Calc;
import com.example.springcalc.component.DollerCalculator;
import com.example.springcalc.component.MarketApi;
import com.example.springcalc.dto.Req;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureWebMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.junit.jupiter.api.Assertions.*;

//web을 테스트할 때 사용 웹에 필요한 빈을 지정하여 테스트 할 수 있다.
//필요하지 않은 것 까지 모두 컨테이너에 올려놓고 사용하지 않기 떄문에
//자원소모를 줄일 수 있다 .
@WebMvcTest(CalcApiController.class)
@AutoConfigureWebMvc
//위의 이유로 SpringBootTest와는 다르게 원하는 클래스를
//@Import를 통해 불러와야한다.
@Import({Calc.class, DollerCalculator.class})
class CalcApiControllerTest {

    //MarketApi는 Mocking 처리를 위해 MockBean으로 지정했다.
    @MockBean
    private MarketApi marketApi;

    //MockMvc 객체 사용을 위해 DI 받았다.
    @Autowired
    private MockMvc mockMvc;

    //모든 테스트 이전에 실행시키고 싶은게 있으면 @BeforEach 사용한다.
    //아래 메서드는 marketApi를 mocking 처리하였다 .
    @BeforeEach
    public void init(){
        Mockito.when(marketApi.connect()).thenReturn(3000);
    }


    //get 요청 테스트
    @Test
    public void sumTest() throws Exception {
        //http://localhost:8080/api/sum

        //테스트가 어떻게 작동할지를 perform안에 정의한다.
        //MockMvcRequestBuilders로 리퀘스트 uri를 만든다
        mockMvc.perform(MockMvcRequestBuilders.get("http://localhost:8080/api/sum")
                //쿼리 파라미터 지정
                .queryParam("x","10")
                .queryParam("y","10")
        //상태코드 예상값 200 지정
        //MockMvcResultMatchers는 값을 예상하는데 쓰이고
        ).andExpect(MockMvcResultMatchers.status().isOk()
        //리턴되는 응답 바디 값 예상
        ).andExpect(MockMvcResultMatchers.content().string("60000")
        //MockMvcResultHandlers는 예상된 값을 처리하는데 쓰인다.
        ).andDo(MockMvcResultHandlers.print());
    }

    @Test
    public void postMinusTest() throws Exception {

        Req req = new Req();
        req.setX(10);
        req.setY(5);
        
        //오브젝트를 json String으로 변경
        String json = new ObjectMapper().writeValueAsString(req);

        mockMvc.perform(MockMvcRequestBuilders.post("http://localhost:8080/api/minus")
        .contentType(MediaType.APPLICATION_JSON)
        .content(json))
                .andExpect(
                       MockMvcResultMatchers.status().isOk()
                )
                .andExpect(
                        //json 노드에 접근해서 비교해준다
                        MockMvcResultMatchers.jsonPath("$.result").value(15000)
                )
                .andExpect(
                        MockMvcResultMatchers.jsonPath("$.respBody.resultCode").value("OK")
                )
                .andDo(MockMvcResultHandlers.print());
    }
}