package urchenkor.vlrutest;

import org.apache.commons.cli.*;
import urchenkor.vlrutest.service.LogProcessor;

import java.util.logging.Logger;

public class Main {
    private static final Logger LOGGER = Logger.getLogger("Main");

    /*
    * -u минимально допустимый уровень доступности (Например "99.9"). Default 99.9
    * -t приемлемое время ответа в миллисекундах (Например 45). Default 45
    */
    public static void main(String[] args) {

        Options options = new Options();
        options.addOption("a", "availability", true, "минимально допустимый уровень доступности (0-100)");
        options.addOption("t", "time", true, "приемлемое время ответа в миллисекундах");

        CommandLineParser parser = new DefaultParser();
        try {
            CommandLine cmd = parser.parse(options, args);
            if (cmd.hasOption("a")) {
                System.out.println(cmd.getOptionValue("a"));
            }
            if (cmd.hasOption("t")) {
                System.out.println(cmd.getOptionValue("t"));
            }
            LogProcessor logProcessor = new LogProcessor();
            logProcessor.processWithBufferedReader();
        } catch (ParseException e) {
            LOGGER.warning("Ошибка чтения параметров запуска: " + e.getMessage());
        }
    }
}