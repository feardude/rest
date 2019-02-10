package ru.smax.rest.service;

import java.math.BigDecimal;
import java.util.Map;

public interface FxRatesService {
    Map<String, BigDecimal> getRates();

    BigDecimal getRate(String baseCurrency, String quoteCurrency);
}
