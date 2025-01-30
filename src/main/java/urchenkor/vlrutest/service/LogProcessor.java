package urchenkor.vlrutest.service;

import urchenkor.vlrutest.model.InputParamsModel;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.logging.Logger;

public class LogProcessor {
    private static final Logger LOGGER = Logger.getLogger("LogParser");

    public void processWithBufferedReader(InputParamsModel params) {
        long start = System.currentTimeMillis();
        LogEventAnalyzer analyzer = new LogEventAnalyzer();
        analyzer.initialize(params);

        //try (BufferedReader br = new BufferedReader(new FileReader("src/main/resources/access.log"))) {
        try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {
            String line;
            while ((line = br.readLine()) != null) {
               analyzer.analyseEvent(line.split(" "));
            }
            analyzer.analyseEvent(null);
        } catch (IOException e) {
            LOGGER.warning("Ошибка чтения файла логов" + e.getMessage());
        }

        long duration = System.currentTimeMillis() - start;
        LOGGER.info("BUFFERED READER parsing time = " + duration + " ms;");
    }
}
