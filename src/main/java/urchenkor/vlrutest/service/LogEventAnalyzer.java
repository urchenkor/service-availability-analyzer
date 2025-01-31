package urchenkor.vlrutest.service;

import urchenkor.vlrutest.model.InputParamsModel;

import java.util.Date;
import java.util.logging.Logger;

import static urchenkor.vlrutest.util.LogParserUtil.*;

/**
 * Анализатор событий логирования для оценки доступности системы.
 */
public class LogEventAnalyzer {
    private static final Logger LOGGER = Logger.getLogger("LogEventAnalyzer");
    private final static String STAT_MESSAGE_TEMPLATE = "%s - %s availability %f";
    private final static String EMPTY_LINE_MESSAGE_TEMPLATE = "Пропуск строки. Считанная строка не содержит обязательного параметра;" +
            "date = %s; statusCode = %s; responseTime = %s;";

    private long totalRequestsCount;
    private long failureRequestsCount;
    private long intervalMs;
    private double maxResponseTime;
    private Date startIntervalDate;
    private double minAvailability;

    /**
     * Анализирует событие логирования и обновляет статистику.
     *
     * @param partedEvent Массив строк, представляющий разбиение события логирования.
     */
    public void analyseEvent(String[] partedEvent) {
        if (partedEvent == null) {
            printStat();
        } else {
            Date currentDate = getDate(partedEvent);
            Integer statusCode = getStatusCode(partedEvent);
            Double responseTime = getResponseTime(partedEvent);

            if (currentDate == null || statusCode == null || responseTime == null) {
                LOGGER.warning(String.format(EMPTY_LINE_MESSAGE_TEMPLATE, currentDate, statusCode, responseTime));
                return;
            }

            //Устанавливаем дату первого интервала.
            if (startIntervalDate == null) {
                startIntervalDate = currentDate;
            }


            if (currentDate.getTime() < (startIntervalDate.getTime() + intervalMs)) {
                totalRequestsCount++;
                if (isUnavailable(statusCode, responseTime)) {
                    failureRequestsCount++;
                }
            } else {
                printStat();
                startIntervalDate = currentDate;
                totalRequestsCount = 0;
                failureRequestsCount = isUnavailable(statusCode, responseTime) ? 1 : 0;
            }
        }
    }

    /**
     * Инициализирует параметры анализатора на основе модели входных параметров.
     *
     * @param inputParamsModel Модель входных параметров.
     */
    public void initialize(InputParamsModel inputParamsModel) {
        totalRequestsCount = 0;
        failureRequestsCount = 0;
        intervalMs = inputParamsModel.getStatisticInterval() * 1000;
        maxResponseTime = inputParamsModel.getMaxResponseTime();
        startIntervalDate = null;
        minAvailability = inputParamsModel.getAvailability();
    }


    /**
     * Проверяет, является ли запрос недоступным.
     *
     * @param statusCode Код статуса HTTP-запроса.
     * @param responseTime Время отклика в миллисекундах.
     * @return true, если статус-код больше 500 или время отклика превышает максимальное значение,
     * иначе false.
     */
    private boolean isUnavailable(Integer statusCode, Double responseTime) {
        return statusCode > 500 || responseTime > maxResponseTime;
    }

    /**
     * Вычисляет текущую доступность системы.
     *
     * @return Доступность в процентах.
     */
    private double calcAvailability() {

        return totalRequestsCount == 0 ? 100 : 100 - ((double) failureRequestsCount / totalRequestsCount * 100);
    }

    /**
     * Печать статистики доступности за интервал времени.
     */
    private void printStat() {
        double availability = calcAvailability();

        if (availability < minAvailability) {
            System.out.println(String.format(STAT_MESSAGE_TEMPLATE, DATE_FORMAT.format(startIntervalDate), DATE_FORMAT.format(new Date(startIntervalDate.getTime() + intervalMs)), availability));
        }
    }
}