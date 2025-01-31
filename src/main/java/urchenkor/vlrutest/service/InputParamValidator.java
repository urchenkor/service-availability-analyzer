package urchenkor.vlrutest.service;

public class InputParamValidator {

    private static final java.util.logging.Logger LOGGER = java.util.logging.Logger.getLogger("LogParser");

    private static final double MIN_AVAILABILITY = 0.0;
    private static final double MAX_AVAILABILITY = 100.0;
    private static final int MIN_MAX_REQUEST_TIME = 1;
    private static final int MAX_MAX_REQUEST_TIME = 1_000_000;
    private static final long MIN_STATISTIC_INTERVAL = 1;
    private static final long MAX_STATISTIC_INTERVAL = Long.MAX_VALUE;

    /**
     * Проверяет корректность входных параметров.
     *
     * @param availability      доступность
     * @param maxRequestTime    максимальное время выполнения запроса
     * @param statisticInterval интервал статистики
     */
    public static void validate(double availability, int maxRequestTime, long statisticInterval) {
        if (availability < MIN_AVAILABILITY || availability > MAX_AVAILABILITY) {
            String message = String.format(
                    "Ошибка в значении параметра availability -a, допустимое значение -a = [%f - %f]",
                    MIN_AVAILABILITY,
                    MAX_AVAILABILITY
            );
            LOGGER.warning(message);
            throw new IllegalArgumentException(message);
        }

        if (maxRequestTime < MIN_MAX_REQUEST_TIME || maxRequestTime > MAX_MAX_REQUEST_TIME) {
            String message = String.format(
                    "Ошибка в значении параметра maxRequestTime -t, допустимое значение -t = [%d - %d]",
                    MIN_MAX_REQUEST_TIME,
                    MAX_MAX_REQUEST_TIME
            );
            LOGGER.warning(message);
            throw new IllegalArgumentException(message);
        }

        if (statisticInterval < MIN_STATISTIC_INTERVAL) {
            String message = String.format(
                    "Ошибка в значении параметра statisticInterval -i, допустимое значение -i = [%d - %d]",
                    MIN_STATISTIC_INTERVAL,
                    MAX_STATISTIC_INTERVAL
            );
            LOGGER.warning(message);
            throw new IllegalArgumentException(message);
        }
    }
}