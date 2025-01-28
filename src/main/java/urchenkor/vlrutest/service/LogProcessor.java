package urchenkor.vlrutest.service;


import urchenkor.vlrutest.model.InputParamsModel;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.logging.Logger;

public class LogProcessor {
    private static final Logger LOGGER = Logger.getLogger("LogParser");

    public void processWithBufferedReader(InputParamsModel params) {
        long start = System.currentTimeMillis();
        long count = 0;
        Parser parser = new Parser();


        try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {
            System.out.println(br.ready());
            String line;
            while ((line = br.readLine()) != null) {
                count++;
                //LOGGER.info(parser.parse(line)[10]);
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
