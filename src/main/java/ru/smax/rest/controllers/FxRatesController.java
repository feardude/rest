package ru.smax.rest.controllers;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.smax.rest.model.FxRate;
import ru.smax.rest.service.FxRatesService;

import java.math.BigDecimal;
import java.util.Map;

@AllArgsConstructor
@RestController
@RequestMapping(path = "/api")
public class FxRatesController {
    private static final String RUB = "RUB";

    private final FxRatesService fxRatesService;

    @GetMapping("/rates")
    public Map<String, BigDecimal> getRates() {
        return fxRatesService.getRates();
    }

    @GetMapping("/rates/{base}")
    public FxRate getRate(@PathVariable(name = "base") String baseCurrency) {
        return FxRate.builder()
                .base(baseCurrency.toUpperCase())
                .quote(RUB)
                .value(fxRatesService.getRate(baseCurrency.toUpperCase(), RUB))
                .build();
    }
}
