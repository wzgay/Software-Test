package com.example.demo.controller;

import com.example.demo.dao.Info;
import com.example.demo.service.DomesticBaggage;
import com.example.demo.service.InternationalBaggage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/baggage")
public class bagging {
    @Autowired
    DomesticBaggage domesticBaggage;
    @Autowired
    InternationalBaggage internationalBaggage;
    @PostMapping
    public Double caculatePrice(@RequestBody Info info){

        if(info.getFlightType().equals("国际航班")){
            return internationalBaggage.calculateInternationalBaggageAllowance(info);
        }else {
            return domesticBaggage.calculateDomesticBaggageBllowance(info);
        }
    }
}
