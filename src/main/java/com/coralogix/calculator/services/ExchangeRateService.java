package com.coralogix.calculator.services;

import java.util.List;

import com.coralogix.calculator.model.ExchangeRate;

public interface ExchangeRateService extends CrudService<ExchangeRate, Integer> {

    public List<ExchangeRate> getExchangeRate(String originCureency, String finalCureency) throws Exception;

    
}
