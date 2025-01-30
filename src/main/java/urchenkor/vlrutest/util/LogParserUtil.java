package urchenkor.vlrutest.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class LogParserUtil {
    public static final SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("dd/MM/yyyy:HH:mm:ss");

    public static final int IP_ADDRESS = 0;
    public static final int REQUEST_TIMESTAMP = 3;
    public static final int REQUEST_TIMESTAMP_ZONE = 4;
    public static final int REQUEST_METHOD = 5;
    public static final int REQUEST_PATH = 6;
    public static final int PROTOCOL_VERSION = 7;
    public static final int STATUS_CODE = 8;
    public static final int RESPONSE_TIME = 10;
    public static final int USER_NAME = 12;
    public static final int PRIORITY = 13;

    public static String getIpAddress(String[] partedLine) {
        return partedLine[IP_ADDRESS];
    }

    public static Date getDate(String[] partedLine){
        try {
            String timestamp = partedLine[REQUEST_TIMESTAMP].substring(1) + " "
                    + partedLine[REQUEST_TIMESTAMP_ZONE];
            return DATE_FORMAT.parse(timestamp);
        } catch (ParseException e) {
            System.err.println(e.getMessage());
            return null;
        }
    }

    public static int getStatusCode(String[] partedLine) {
        return Integer.parseInt(partedLine[STATUS_CODE]);
    }

    public static double getResponseTime(String[] partedLine) {
        return Double.parseDouble(partedLine[RESPONSE_TIME]);
    }

    public static String getRequestMethod(String[] partedLine) {
        return partedLine[REQUEST_METHOD].substring(1);
    }

    public static String getRequestPath(String[] partedLine) {
        return partedLine[REQUEST_PATH];
    }

    public static String getProtocolVersion(String[] partedLine) {
        return partedLine[PROTOCOL_VERSION].substring(0, partedLine[PROTOCOL_VERSION].length()-1);
    }

    public static String getUserName(String[] partedLine) {
        return partedLine[USER_NAME].substring(1, partedLine[USER_NAME].length()-1);
    }

    public static int getPriority(String[] partedLine) {
        return Integer.parseInt(partedLine[PRIORITY].substring(partedLine[PRIORITY].length()-1));
    }

}