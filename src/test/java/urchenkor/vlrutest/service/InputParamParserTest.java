package urchenkor.vlrutest.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.apache.commons.cli.ParseException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import urchenkor.vlrutest.model.InputParamsModel;

class InputParamParserTest {

    private InputParamParser inputParamParser;

    @BeforeEach
    void setUp() {
        inputParamParser = new InputParamParser();
    }

    @Test
    void testValidArguments() throws ParseException {
        // Дефолтный интервал статистики
        String[] args = {"-a", "99.9", "-t", "500"};
        InputParamsModel result = inputParamParser.parse(args);
        assertEquals(99.9, result.getAvailability(), 0.001);
        assertEquals(500, result.getMaxResponseTime());
        assertEquals(60L, result.getStatisticInterval());

        // Указанный интервал статистики
        args = new String[]{"-a", "95.0", "-t", "2000", "-i", "120"};
        result = inputParamParser.parse(args);
        assertEquals(95.0, result.getAvailability(), 0.01);
        assertEquals(2000, result.getMaxResponseTime());
        assertEquals(120L, result.getStatisticInterval());
    }

    @Test
    void testMissingRequiredArgument() {
        String[] args = {"-a", "90.0"}; // Отсутствует параметр maxRequestTime
        Exception exception = assertThrows(RuntimeException.class, () -> inputParamParser.parse(args));
        assertEquals("Отсутствуют обязательные параметры", exception.getMessage());
    }

    @Test
    void testInvalidArguments() {
        // Недопустимое значение для availability
        Exception exception = assertThrows(NumberFormatException.class, () -> inputParamParser.parse(new String[]{"-a", "invalid", "-t", "500"}));
        assertEquals("For input string: \"invalid\"", exception.getMessage());

        // Значение availability больше 100%
        exception = assertThrows(IllegalArgumentException.class, () -> inputParamParser.parse(new String[]{"-a", "101.0", "-t", "500"}));
        assertEquals("Ошибка в значении параметра availability -a, допустимое значение -a = [0,000000 - 100,000000]", exception.getMessage());

        // Недопустимое значение для maxRequestTime
        exception = assertThrows(NumberFormatException.class, () -> inputParamParser.parse(new String[]{"-a", "50.0", "-t", "invalid"}));
        assertEquals("For input string: \"invalid\"", exception.getMessage());

        // Значение maxRequestTime превышает максимально допустимое
        exception = assertThrows(IllegalArgumentException.class, () -> inputParamParser.parse(new String[]{"-a", "50.0", "-t", "2000000"}));
        assertEquals("Ошибка в значении параметра maxRequestTime -t, допустимое значение -t = [1 - 1000000]", exception.getMessage());

        // Неверное значение для интервала статистики
        exception = assertThrows(IllegalArgumentException.class, () -> inputParamParser.parse(new String[]{"-a", "50.0", "-t", "2000", "-i", "0"}));
        assertEquals("Ошибка в значении параметра statisticInterval -i, допустимое значение -i = [1 - 9223372036854775807]", exception.getMessage());
    }
}