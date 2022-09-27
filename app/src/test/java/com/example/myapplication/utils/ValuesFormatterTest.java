package com.example.myapplication.utils;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class ValuesFormatterTest {

    @Test
    public void givenAPriceInIntegerItReturnsA£FormattedDetail() {
        Integer APPLE_PRICE_MOCK = 149;
        String APPLE_PRICE_EXPECTED = "£ 1.49p";
        assertEquals(APPLE_PRICE_EXPECTED, ValuesFormatter.priceFormatter(APPLE_PRICE_MOCK));
    }

    @Test
    public void givenA99priceItReturns99p() {
        Integer FRUIT_PRICE_MOCK = 99;
        String FRUIT_PRICE_EXPECTED = "99p";
        assertEquals(FRUIT_PRICE_EXPECTED, ValuesFormatter.priceFormatter(FRUIT_PRICE_MOCK));
    }

    @Test
    public void givenA0PriceItReturns0p() {
        Integer FRUIT_99p_PRICE_MOCK = 0;
        String FRUIT_PRICE_EXPECTED = "0p";
        assertEquals(FRUIT_PRICE_EXPECTED, ValuesFormatter.priceFormatter(FRUIT_99p_PRICE_MOCK));
    }

    @Test
    public void givenAWeightOf5000gItReturnsAKgFormattedDetail() {
        Integer APPLE_WEIGHT_5000g_MOCK = 5000;
        String APPLE_WEIGHT_EXPECTED = "Kg 5.000";
        assertEquals(APPLE_WEIGHT_EXPECTED, ValuesFormatter.weightFormatter(APPLE_WEIGHT_5000g_MOCK));
    }

    @Test
    public void givenAWeightOf500gItReturnsAKgFormattedDetail() {
        Integer APPLE_WEIGHT_500g_MOCK = 500;
        String APPLE_WEIGHT_EXPECTED = "Kg 0.500";
        assertEquals(APPLE_WEIGHT_EXPECTED, ValuesFormatter.weightFormatter(APPLE_WEIGHT_500g_MOCK));
    }

    @Test
    public void givenALowerStringResponseItReturnsACapitalisedString() {
        String FRUIT_TYPE_MOCK = "apple";
        String FRUIT_TYPE_EXPECTED = "Apple";
        assertEquals(FRUIT_TYPE_EXPECTED, ValuesFormatter.capitalize(FRUIT_TYPE_MOCK));
    }
}