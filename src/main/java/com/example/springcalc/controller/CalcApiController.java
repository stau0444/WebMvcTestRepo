package com.example.springcalc.controller;

import com.example.springcalc.component.Calc;
import com.example.springcalc.dto.Req;
import com.example.springcalc.dto.Res;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class CalcApiController {

    private final Calc calc;

    @GetMapping("/sum")
    public int sum(@RequestParam int x , @RequestParam int y){
        return calc.sum(x,y);
    }

    @GetMapping("/minus")
    public int minus(@RequestParam int x , @RequestParam int y){
        return calc.minus(x,y);
    }

    @PostMapping("/sum")
    public Res postSum(@RequestBody Req req){

        int result = calc.sum(req.getX(),req.getY());

         Res res = new Res();
         res.setResult(result);
         res.setRespBody(new Res.Body());

        return res;
    }
    @PostMapping("/minus")
    public Res postMinus(@RequestBody Req req){

        int result = calc.minus(req.getX(),req.getY());

        Res res = new Res();
        res.setResult(result);
        res.setRespBody(new Res.Body());

        return res;
    }
}
