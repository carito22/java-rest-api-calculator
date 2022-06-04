package com.coralogix.calculator.model;


import lombok.Data;

@Data
public class ExchangeRateExternoTO {
    private boolean success;
    private Long timestamp;
    private String base;
    private String date;
    Object rates;
}
