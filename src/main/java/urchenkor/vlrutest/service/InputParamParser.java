package urchenkor.vlrutest.service;

import org.apache.commons.cli.*;
import urchenkor.vlrutest.model.InputParamsModel;

import java.util.logging.Logger;

public class InputParamParser {
    private static final Logger LOGGER = Logger.getLogger("InputParamParser");

    public InputParamsModel parse(String[] args) {
        Options options = new Options();
        options.addOption("a", "availability", true, "минимально допустимый уровень доступности (0.00-100.00)");
        options.addOption("t", "time", true, "приемлемое время ответа в миллисекундах (1 - 1000000");

        CommandLineParser parser = new DefaultParser();
        try {
            CommandLine cmd = parser.parse(options, args);
            if (cmd.hasOption("a") && cmd.hasOption("t")) {
                double a = Double.parseDouble(cmd.getOptionValue("a"));
                int t = Integer.parseInt(cmd.getOptionValue("t"));
                InputParamValidator.validate(a, t);
                return new InputParamsModel(a, t);
            }
            throw new RuntimeException("Отсутствуют обязательные параметры");
        } catch (ParseException e) {
            throw new RuntimeException("Ошибка чтения параметров запуска: " + e.getMessage());
        }
    }
}
