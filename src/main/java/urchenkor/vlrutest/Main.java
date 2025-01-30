package urchenkor.vlrutest;

import urchenkor.vlrutest.model.InputParamsModel;
import urchenkor.vlrutest.service.InputParamParser;
import urchenkor.vlrutest.service.LogProcessor;

public class Main {

    public static void main(String[] args) {

        InputParamParser inputParamParser = new InputParamParser();
        InputParamsModel params = inputParamParser.parse(args);
        LogProcessor logProcessor = new LogProcessor();
        logProcessor.processWithBufferedReader(params);
    }
}