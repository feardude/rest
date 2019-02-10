package ru.smax.rest.controllers;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.smax.rest.service.FxRatesService;

import java.math.BigDecimal;
import java.util.Map;

@AllArgsConstructor
@RestController
@RequestMapping(path = "/api")
public class FxRateController {
    private final FxRatesService fxRatesService;

    @GetMapping("/rates")
    public Map<String, BigDecimal> getRates() {
        return fxRatesService.getRates();
    }
}
