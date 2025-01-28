package urchenkor.vlrutest;

import org.apache.commons.cli.*;
import urchenkor.vlrutest.model.InputParamsModel;
import urchenkor.vlrutest.service.InputParamParser;
import urchenkor.vlrutest.service.LogProcessor;

import java.util.logging.Logger;

public class Main {
    private static final Logger LOGGER = Logger.getLogger("Main");

    /*
     * -u минимально допустимый уровень доступности (Например "99.9"). Default 99.9
     * -t приемлемое время ответа в миллисекундах (Например 45). Default 45
     */
    public static void main(String[] args) {

        InputParamParser inputParamParser = new InputParamParser();
        InputParamsModel params = inputParamParser.parse(args);
        LogProcessor logProcessor = new LogProcessor();
        logProcessor.processWithBufferedReader(params);
    }
}