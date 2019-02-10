package ru.smax.rest.service;

import lombok.AllArgsConstructor;

import java.math.BigDecimal;
import java.util.Map;
import java.util.Optional;

import static java.math.BigDecimal.ONE;
import static java.math.BigDecimal.ZERO;
import static java.math.RoundingMode.HALF_UP;

@AllArgsConstructor
public class FxRatesServiceImpl implements FxRatesService {
    private static final String RUB = "RUB";
    private static final int SCALE = 10;

    private final Map<String, BigDecimal> fxRates;

    @Override
    public Map<String, BigDecimal> getRates() {
        return fxRates;
    }

    @Override
    public BigDecimal getRate(String baseCurrency, String quoteCurrency) {
        if (baseCurrency.equals(quoteCurrency)) {
            return ONE;
        }

        if (quoteCurrency.equals(RUB)) {
            return fxRates.getOrDefault(baseCurrency, ZERO);
        }

        if (baseCurrency.equals(RUB)) {
            return calcInverseRate(quoteCurrency);
        }

        final BigDecimal baseCurrencyRate = fxRates.getOrDefault(baseCurrency, ZERO);
        final BigDecimal quoteCurrencyRate = fxRates.getOrDefault(quoteCurrency, ZERO);

        return calcCrossRate(baseCurrencyRate, quoteCurrencyRate);
    }

    private BigDecimal calcInverseRate(String currency) {
        return Optional.ofNullable(fxRates.get(currency))
                .map(rate -> calcCrossRate(ONE, rate))
                .orElse(ZERO);
    }

    private BigDecimal calcCrossRate(BigDecimal baseCurrencyRate, BigDecimal quoteCurrencyRate) {
        return quoteCurrencyRate.equals(ZERO)
                ? ZERO
                : baseCurrencyRate.divide(quoteCurrencyRate, SCALE, HALF_UP);
    }
}
