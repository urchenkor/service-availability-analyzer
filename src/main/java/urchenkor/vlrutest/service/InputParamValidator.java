package urchenkor.vlrutest.service;

public class InputParamValidator {
    public static void validate(double availability, int maxRequestTime, long statisticInterval) {
        if (availability > 100 || availability < 0) {
            throw new RuntimeException("Ошибка в значении параметра availability -a, допустимые значение -а = [0.00 - 100.00]");
        }
        if (maxRequestTime > 1000000 || maxRequestTime < 1) {
            throw new RuntimeException("Ошибка в значении параметра maxRequestTime -t, допустимые значение -t = [1 - 1000000]");
        }
        if (statisticInterval < 1) {
            throw new RuntimeException("Ошибка в значении параметра statisticInterval -i, допустимые значение -i > 1");
        }
    }
}
