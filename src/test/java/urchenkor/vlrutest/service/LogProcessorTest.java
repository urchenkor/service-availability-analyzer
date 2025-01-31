package urchenkor.vlrutest.service;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import urchenkor.vlrutest.model.InputParamsModel;

import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class LogProcessorTest {


    private final ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();

    private LogProcessor logProcessor;

    @BeforeEach
    void setUp() {
        //MockitoAnnotations.initMocks(this);
        logProcessor = new LogProcessor();
        System.setOut(new PrintStream(outputStreamCaptor));
    }

    @Test
    public void parseCase1() throws FileNotFoundException {
        //given
        System.setIn(new FileInputStream("src/test/resources/access.log"));
        InputParamsModel inputParams = new InputParamsModel(99.0, 90.0, 30);
        String[] expected = new String[]{
                "14/06/2017:16:47:02 - 14/06/2017:16:47:32 availability 92,384888\r",
                "14/06/2017:16:47:32 - 14/06/2017:16:48:02 availability 95,114007\r",
                "14/06/2017:16:48:02 - 14/06/2017:16:48:32 availability 95,588972\r",
                "14/06/2017:16:48:32 - 14/06/2017:16:49:02 availability 94,690613\r"
        };

        //then
        logProcessor.processWithBufferedReader(inputParams);

        String[] actual = outputStreamCaptor.toString().split("\n");
        assertEquals(expected.length, actual.length);

        assertArrayEquals(expected, actual);
    }

    @Test
    public void parseCase2() throws FileNotFoundException {
        //given
        System.setIn(new FileInputStream("src/test/resources/access.log"));
        InputParamsModel inputParams = new InputParamsModel(99.0, 90.0, 240);
        String[] expected = new String[]{
                "14/06/2017:16:47:02 - 14/06/2017:16:51:02 availability 94,680086\r"
        };

        //then
        logProcessor.processWithBufferedReader(inputParams);
        String[] actual = outputStreamCaptor.toString().split("\n");
        assertEquals(expected.length, actual.length);

        assertArrayEquals(expected, actual);
    }


    @Test
    public void parseCase3() throws FileNotFoundException {
        //given
        System.setIn(new FileInputStream("src/test/resources/access.log"));
        InputParamsModel inputParams = new InputParamsModel(90.0, 90.0, 240);

        //then
        logProcessor.processWithBufferedReader(inputParams);

        String[] actual = outputStreamCaptor.toString().split("\n");
        assertEquals(1, actual.length);
    }
}