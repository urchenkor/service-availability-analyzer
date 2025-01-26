package urchenkor.vlrutest.service;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.logging.Logger;

public class LogProcessor {
    private static final Logger LOGGER = Logger.getLogger("LogParser");

    public void processWithBufferedReader() {
        long start = System.currentTimeMillis();
        long count = 0;
        Parser parser = new Parser();

        try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {
            while (br.ready()) {
                count++;
                LOGGER.info(parser.parse(br.readLine())[10]);
                /*String[] result = parser.parse(br.readLine());
                LOGGER.info("STATUS: " + result[8] + "; DURATION: " + result[10] + " ms;");*/
            }
        } catch (IOException e) {
            LOGGER.warning("Ошибка чтения файла логов" + e.getMessage());
        }

        long duration = System.currentTimeMillis() - start;
        LOGGER.info("BUFFERED READER parsing time = " + duration + " ms;" + "count = " + count);
    }
}
