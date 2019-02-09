package ru.smax.rest.service;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import static java.lang.String.format;

@RestController
@RequestMapping(path = "/api")
public class Controller {
    @GetMapping("/hello")
    public String test(@RequestParam String name) {
        return format("Hello, %s!", name);
    }
}
