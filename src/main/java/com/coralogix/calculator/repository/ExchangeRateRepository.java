package com.coralogix.calculator.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.coralogix.calculator.model.ExchangeRate;

public interface ExchangeRateRepository extends GenericRepository<ExchangeRate, Integer> {

    @Query(value = "SELECT * FROM exchange_rate  WHERE origin_currency = :originCurrency AND final_cureency= :finalCureency", nativeQuery = true)
    public List<ExchangeRate> getExchangeRateByOriginFinal(@Param("originCurrency") String originCurrencyname,
            @Param("finalCureency") String finalCureency);

}
