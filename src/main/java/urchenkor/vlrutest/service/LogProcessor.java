package urchenkor.vlrutest.service;


import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.SeekableByteChannel;
import java.nio.charset.StandardCharsets;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.Scanner;
import java.util.SplittableRandom;
import java.util.concurrent.atomic.AtomicLong;
import java.util.logging.Logger;
import java.util.stream.Stream;

public class LogProcessor {
    private static final Logger LOGGER = Logger.getLogger("LogParser");

    public void processWithScanner(String filePath) {
        long start = System.currentTimeMillis();
        long count = 0;
        try (
                FileInputStream inputStream = new FileInputStream(filePath);
                Scanner sc = new Scanner(inputStream, StandardCharsets.UTF_8)
        ) {
            while (sc.hasNextLine()) {
                String line = sc.nextLine();
                count++;
            }
            // note that Scanner suppresses exceptions
            if (sc.ioException() != null) {
                throw sc.ioException();
            }
        } catch (IOException e) {
            LOGGER.info("Ошибка чтения файла логов" + e.getMessage());
        }

        long duration = System.currentTimeMillis() - start;
        LOGGER.info("SCANNER parsing time = " + duration + " ms;" + "count = " + count);
    }

    public void processWithBufferedReader(String filePath) {
        long start = System.currentTimeMillis();
        long count = 0;
        Parser parser = new Parser();

        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            while (br.ready()) {
                count++;
                LOGGER.info(parser.parse(br.readLine())[10]);
                /*String[] result = parser.parse(br.readLine());
                LOGGER.info("STATUS: " + result[8] + "; DURATION: " + result[10] + " ms;");*/
            }
        } catch (IOException e) {
            LOGGER.info("Ошибка чтения файла логов" + e.getMessage());
        }

        long duration = System.currentTimeMillis() - start;
        LOGGER.info("BUFFERED READER parsing time = " + duration + " ms;" + "count = " + count);
    }


    public void processWithStream(String filePath) {
        long start = System.currentTimeMillis();
        AtomicLong count = new AtomicLong();
        try (Stream<String> lines = java.nio.file.Files.lines(Paths.get(filePath))) {
            lines.forEach(line -> {
                count.getAndIncrement();
            });
        } catch (IOException e) {
            LOGGER.info("Ошибка чтения файла логов" + e.getMessage());
        }

        long duration = System.currentTimeMillis() - start;
        LOGGER.info("STREAM parsing time = " + duration + " ms;" + "count = " + count);
    }


}
