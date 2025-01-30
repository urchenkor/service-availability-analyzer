package urchenkor.vlrutest.util;

import org.junit.jupiter.api.Test;

import java.text.ParseException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static urchenkor.vlrutest.util.LogParserUtil.DATE_FORMAT;

public class LogParserUtilTest {

    String[] partedLine =
            "192.168.32.181 - - [14/06/2017:16:47:02 +1000] \"PUT /rest/v1.4/documents?zone=default&_rid=6076537c HTTP/1.1\" 200 2 44.510983 \"-\" \"@list-item-updater\" prio:0"
                    .split(" ");

    @Test
    void getIpAddress() {
        assertEquals("192.168.32.181", LogParserUtil.getIpAddress(partedLine));
    }

    @Test
    void getDate() throws ParseException {
        assertEquals(DATE_FORMAT.parse("14/06/2017:16:47:02 +1000"), LogParserUtil.getDate(partedLine));
    }

    @Test
    void getStatusCode() {
        assertEquals(200, LogParserUtil.getStatusCode(partedLine));
    }

    @Test
    void getResponseTime() {
        assertEquals(44.510983, LogParserUtil.getResponseTime(partedLine));
    }

    @Test
    void getRequestMethod() {
        assertEquals("PUT", LogParserUtil.getRequestMethod(partedLine));
    }

    @Test
    void getRequestPath() {
        assertEquals("/rest/v1.4/documents?zone=default&_rid=6076537c", LogParserUtil.getRequestPath(partedLine));
    }

    @Test
    void getProtocolVersion() {
        assertEquals("HTTP/1.1", LogParserUtil.getProtocolVersion(partedLine));
    }

    @Test
    void getUserName() {
        assertEquals("@list-item-updater", LogParserUtil.getUserName(partedLine));
    }

    @Test
    void getPriority() {
        assertEquals(0, LogParserUtil.getPriority(partedLine));
    }
}
