package ru.smax.rest.service;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.util.Map;

import static java.lang.String.format;

@AllArgsConstructor
@RestController
@RequestMapping(path = "/api")
public class Controller {
    private final FxRatesService fxRatesService;

    @GetMapping("/hello")
    public String test(@RequestParam String name) {
        return format("Hello, %s!", name);
    }

    @GetMapping("/rates")
    public Map<String, BigDecimal> getRates() {
        return fxRatesService.getRates();
    }
}
