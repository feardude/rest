package ru.smax.rest.service;

import org.junit.Test;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

import static java.math.BigDecimal.ONE;
import static java.math.BigDecimal.ZERO;
import static org.junit.Assert.assertEquals;

public class FxRatesServiceTest {
    private static final Map<String, BigDecimal> FX_RATES;

    static {
        FX_RATES = new HashMap<>(1);
        FX_RATES.put("USD", BigDecimal.valueOf(66.0199));
        FX_RATES.put("EUR", BigDecimal.valueOf(74.9656));
    }

    private final FxRatesService fxRatesService = new FxRatesServiceImpl(FX_RATES);

    @Test
    public void singularRate() {
        assertEquals(
                ONE,
                fxRatesService.getRate("RUB", "RUB")
        );
    }

    @Test
    public void usdRub() {
        final BigDecimal expected = FX_RATES.get("USD");
        final BigDecimal actual = fxRatesService.getRate("USD", "RUB");
        assertEquals(expected, actual);
    }

    @Test
    public void rubUsd() {
        final BigDecimal expected = BigDecimal.valueOf(0.0151469481);
        final BigDecimal actual = fxRatesService.getRate("RUB", "USD");
        assertEquals(expected, actual);
    }

    @Test
    public void usdEur() {
        final BigDecimal expected = BigDecimal.valueOf(8806692670L, 10);
        final BigDecimal actual = fxRatesService.getRate("USD", "EUR");
        assertEquals(expected, actual);
    }

    @Test
    public void gbpRub() {
        assertEquals(
                ZERO,
                fxRatesService.getRate("GBP", "RUB")
        );
    }

    @Test
    public void rubGbp() {
        assertEquals(
                ZERO,
                fxRatesService.getRate("RUB", "GBP")
        );
    }
}
