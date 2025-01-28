package urchenkor.vlrutest.service;

public class InputParamValidator {
    public static void validate(double availability, int time) {
        if (availability > 100 || availability < 0) {
            throw new RuntimeException("Ошибка в значении параметра availability -a, допустимые значение -а = [0.00 - 100.00]");
        }
        if (time > 1000000 || time < 1) {
            throw new RuntimeException("Ошибка в значении параметра time -t, допустимые значение -t = [1 - 1000000]");
        }
    }
}
