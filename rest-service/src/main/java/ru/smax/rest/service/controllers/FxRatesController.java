package ru.smax.rest.service.controllers;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ru.smax.rest.service.FxRatesService;
import ru.smax.rest.service.model.FxRate;

import java.util.List;

import static java.math.BigDecimal.ONE;
import static java.util.stream.Collectors.toList;

@AllArgsConstructor
@Slf4j
@RestController
@RequestMapping(path = "/api")
public class FxRatesController {
    private static final String RUB = "RUB";

    private final FxRatesService fxRatesService;

    @GetMapping("/rates")
    public List<FxRate> getRates() {
        log.info("Requested rates");

        return fxRatesService.getRates().entrySet().stream()
                .map(codeToRate -> FxRate.builder()
                        .base(codeToRate.getKey())
                        .quote(RUB)
                        .value(codeToRate.getValue())
                        .build())
                .collect(toList());
    }

    @GetMapping("/rates/{base}")
    public FxRate getRate(@PathVariable(name = "base") String baseCurrency,
                          @RequestParam(name = "quote", defaultValue = RUB) String quoteCurrency) {

        log.info("Requested rate [base={}, quote={}]", baseCurrency, quoteCurrency);

        final String baseCode = baseCurrency.toUpperCase();
        final String quoteCode = quoteCurrency.toUpperCase();

        if (baseCode.equals(quoteCode)) {
            return toSingularRate(baseCode);
        }

        return FxRate.builder()
                .base(baseCode)
                .quote(quoteCode)
                .value(fxRatesService.getRate(baseCode, quoteCode))
                .build();
    }

    private FxRate toSingularRate(String currency) {
        return FxRate.builder()
                .base(currency)
                .quote(currency)
                .value(ONE)
                .build();
    }
}
