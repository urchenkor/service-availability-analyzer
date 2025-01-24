package urchenkor.vlrutest.service;


import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class LogProcessorTest {



    @Test
    public void parseWithScanner() {
        LogProcessor logProcessor = new LogProcessor();
        //logProcessor.processWithScanner("src/main/resources/access.log");
        logProcessor.processWithBufferedReader("src/main/resources/test.log");
        //logProcessor.processWithStream("src/main/resources/access.log");
        assertTrue(true);
    }
}