package com.coralogix.calculator;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.coralogix.calculator.model.ExchangeRate;
import com.coralogix.calculator.services.ExchangeRateService;

@RestController
@RequestMapping("exchange")
public class ExchangeRateController {

    @Autowired
    private ExchangeRateService service;

    @GetMapping("/getAllExchangeRate")
    public ResponseEntity<List<ExchangeRate>> getAllExchangeRate() throws Exception {
        List<ExchangeRate> listado = service.getAll();
        return new ResponseEntity<>(listado, HttpStatus.OK);
    }

    @GetMapping("/getExchangeRate")
    public ResponseEntity<List<ExchangeRate>>  getExchangeRate(@RequestParam String originCurrency, @RequestParam String finalCureency)throws Exception   {
        List<ExchangeRate> listado = service.getExchangeRate(originCurrency, finalCureency);
        return new ResponseEntity<>(listado, HttpStatus.OK);
    }

}
