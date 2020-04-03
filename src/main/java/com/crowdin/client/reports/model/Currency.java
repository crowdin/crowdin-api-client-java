package com.crowdin.client.reports.model;

import com.crowdin.client.core.model.EnumConverter;

public enum Currency implements EnumConverter<Currency> {

    USD, EUR, JPY, GBP, AUD, CAD, CHF, CNY, SEK, NZD, MXN, SGD, HKD, NOK, KRW, TRY, RUB, INR, BRL, ZAR, GEL, UAH;

    public static Currency from(String value) {
        return Currency.valueOf(value);
    }

    @Override
    public String to(Currency v) {
        return v.name();
    }
}
