package urchenkor.vlrutest.service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.assertThrows;

class InputParamValidatorTest {

    @ParameterizedTest
    @ValueSource(doubles = {-1.0, 101.0})
    void shouldThrowExceptionForInvalidAvailability(double availability) {
        int maxRequestTime = 10;
        long statisticInterval = 20L;

        assertThrows(IllegalArgumentException.class, () ->
                InputParamValidator.validate(availability, maxRequestTime, statisticInterval));
    }

    @ParameterizedTest
    @ValueSource(ints = {0, 1000001})
    void shouldThrowExceptionForInvalidMaxRequestTime(int maxRequestTime) {
        double availability = 50.0;
        long statisticInterval = 20L;

        assertThrows(IllegalArgumentException.class, () ->
                InputParamValidator.validate(availability, maxRequestTime, statisticInterval));
    }

    @ParameterizedTest
    @ValueSource(longs = {0})
    void shouldThrowExceptionForInvalidStatisticInterval(long statisticInterval) {
        double availability = 50.0;
        int maxRequestTime = 10;

        assertThrows(IllegalArgumentException.class, () ->
                InputParamValidator.validate(availability, maxRequestTime, statisticInterval));
    }

    @Test
    void shouldNotThrowExceptionForValidParameters() {
        double availability = 50.0;
        int maxRequestTime = 500;
        long statisticInterval = 60L;

        InputParamValidator.validate(availability, maxRequestTime, statisticInterval); // No exception expected
    }
}