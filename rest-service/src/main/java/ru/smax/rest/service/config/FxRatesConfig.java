package ru.smax.rest.service.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import ru.smax.rest.service.FxRatesService;
import ru.smax.rest.service.FxRatesServiceImpl;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

@Configuration
public class FxRatesConfig {
    @Bean
    public FxRatesService fxRatesService(Map<String, BigDecimal> fxRates) {
        return new FxRatesServiceImpl(fxRates);
    }

    @Bean
    public Map<String, BigDecimal> fxRates() {
        final HashMap<String, BigDecimal> fxRates = new HashMap<>();
        fxRates.put("USD", BigDecimal.valueOf(66.0199));
        fxRates.put("EUR", BigDecimal.valueOf(74.9656));
        fxRates.put("GBP", BigDecimal.valueOf(85.4324));
        fxRates.put("CHF", BigDecimal.valueOf(65.9507));
        return fxRates;
    }
}
