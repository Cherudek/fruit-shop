package com.example.myapplication.utils;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class StringFormatterTest {

    @Test
    public void givenAPriceInIntegerItReturnsA£FormattedDetail() {
        Integer APPLE_PRICE_MOCK = 149;
        String APPLE_PRICE_EXPECTED = "£ 1.49p";
        assertEquals(APPLE_PRICE_EXPECTED, StringFormatter.priceFormatter(APPLE_PRICE_MOCK));
    }

    @Test
    public void givenAWeightInIntegerItReturnsAKgFormattedDetail() {
        Integer APPLE_WIGHT_MOCK = 120;
        String APPLE_WEIGHT_EXPECTED = "Kg 1.20";
        assertEquals(APPLE_WEIGHT_EXPECTED, StringFormatter.weightFormatter(APPLE_WIGHT_MOCK));
    }

    @Test
    public void givenALowerStringResponseItReturnsACapitalisedString() {
        String FRUIT_TYPE_MOCK = "apple";
        String FRUIT_TYPE_EXPECTED = "Apple";
        assertEquals(FRUIT_TYPE_EXPECTED, StringFormatter.capitalize(FRUIT_TYPE_MOCK));

    }
}