package urchenkor.vlrutest.service;

import org.apache.commons.cli.*;
import urchenkor.vlrutest.model.InputParamsModel;

public class InputParamParser {

    public InputParamsModel parse(String[] args) {
        Options options = new Options();
        String AVAILABILITY = "a";
        String MAX_REQUEST_TIME = "t";
        String STATISTIC_INTERVAL = "i";

        options.addOption(AVAILABILITY, "availability", true, "минимально допустимый уровень доступности (0.00-100.00)");
        options.addOption(MAX_REQUEST_TIME, "maxRequestTime", true, "приемлемое время ответа в миллисекундах (1 - 1000000");
        options.addOption(STATISTIC_INTERVAL, "statisticInterval", true, "Интервал отображаемой статистики в секундах (Дефолтное значение 60");

        CommandLineParser parser = new DefaultParser();
        try {
            CommandLine cmd = parser.parse(options, args);
            if (cmd.hasOption(AVAILABILITY) && cmd.hasOption(MAX_REQUEST_TIME)) {
                double a = Double.parseDouble(cmd.getOptionValue(AVAILABILITY));
                int t = Integer.parseInt(cmd.getOptionValue(MAX_REQUEST_TIME));
                long i = cmd.hasOption(STATISTIC_INTERVAL) ? Long.parseLong(cmd.getOptionValue(STATISTIC_INTERVAL)) : 60;
                InputParamValidator.validate(a, t, i);
                return new InputParamsModel(a, t, i);
            }
            throw new RuntimeException("Отсутствуют обязательные параметры");
        } catch (ParseException e) {
            throw new RuntimeException("Ошибка чтения параметров запуска: " + e.getMessage());
        }
    }
}
