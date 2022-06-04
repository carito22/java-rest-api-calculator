package com.coralogix.calculator.services;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.coralogix.calculator.model.ExchangeRate;
import com.coralogix.calculator.model.ExchangeRateExternoTO;
import com.coralogix.calculator.repository.ExchangeRateRepository;
import com.coralogix.calculator.repository.GenericRepository;
import com.coralogix.calculator.utils.UtilsJSON;

import org.springframework.web.client.RestTemplate;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

@Service
public class ExchangeRateServiceImpl extends CrudServiceImpl<ExchangeRate, Integer> implements ExchangeRateService {

    @Autowired
    private ExchangeRateRepository repo;

    @Override
    protected GenericRepository<ExchangeRate, Integer> getRepository() {
        return repo;
    }

    @Override
    public List<ExchangeRate> getExchangeRate(String originCureency, String finalCureency) throws Exception {
        List<ExchangeRate> lista = new ArrayList<>();

        lista = repo.getExchangeRateByOriginFinal(originCureency, finalCureency);
        if (lista == null || lista.size() == 0) {
            // CONSULTAR API EXTERNA Y GUARDARLO EN BD
            String url = "http://localhost:3000/fixer/latest?base=" + originCureency + "&symbols=" + finalCureency;
            ExchangeRateExternoTO result = new RestTemplate().getForObject(url, ExchangeRateExternoTO.class);

            if (result != null) {
                LinkedHashMap<String, Double> ratesLink = (LinkedHashMap<String, Double>) result.getRates();

                if (ratesLink != null) {
                    Double value = null;
                    for (Map.Entry<String, Double> i : ratesLink.entrySet()) {
                        value = i.getValue();
                    }
                    ExchangeRate ex = new ExchangeRate();
                    ex.setDate(result.getDate());
                    ex.setOriginCurrency(result.getBase());
                    ex.setFinalCureency(finalCureency);
                    ex.setValue(value != null ? String.valueOf(value) : "0.00");

                    repo.save(ex);
                    lista.add(ex);
                }
            }
        }
        return lista;
    }

}
