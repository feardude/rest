package ru.smax.rest.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

import java.math.BigDecimal;

@AllArgsConstructor
@Builder
@Getter
@EqualsAndHashCode
@ToString
public class FxRate {
    private final String base;
    private final String quote;
    private final BigDecimal value;
}
