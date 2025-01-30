package urchenkor.vlrutest.service;

import urchenkor.vlrutest.model.InputParamsModel;

import java.util.Date;

import static urchenkor.vlrutest.util.LogParserUtil.*;

public class LogEventAnalyzer {
    private final static String STAT_MESSAGE_TEMPLATE = "%s - %s availability %f";

    private long totalRequestsCount;
    private long failureRequestsCount;
    private long intervalMs;
    private double maxResponseTime;
    private Date startIntervelDate;
    private double minAvailability;

    public void analyseEvent(String[] partedEvent) {
        if (partedEvent == null) {
            printStat();
        } else {
            Date currentDate = getDate(partedEvent);
            if (startIntervelDate == null) {

                startIntervelDate = currentDate;
            }

            if (currentDate.getTime() < (startIntervelDate.getTime() + intervalMs)) {
                totalRequestsCount++;
                if (isUnavailable(getStatusCode(partedEvent), getResponseTime(partedEvent))) {
                    failureRequestsCount++;
                }
            } else {
                startIntervelDate = currentDate;
                printStat();
                totalRequestsCount = 0;
                failureRequestsCount = isUnavailable(getStatusCode(partedEvent), getResponseTime(partedEvent)) ? 1 : 0;
            }
        }
    }

    public void initialize(InputParamsModel inputParamsModel) {
        totalRequestsCount = 0;
        failureRequestsCount = 0;
        intervalMs = inputParamsModel.getStatisticInterval() * 1000;
        maxResponseTime = inputParamsModel.getMaxResponseTime();
        startIntervelDate = null;
        minAvailability = inputParamsModel.getAvailability();
    }

    private boolean isUnavailable(int statusCode, double responseTime) {
        return statusCode > 500 || responseTime > maxResponseTime;
    }

    private double calcAvailability() {

        return totalRequestsCount == 100 ? 100 : 100 - ((double) failureRequestsCount / totalRequestsCount * 100);
    }

    private void printStat() {
        double availability = calcAvailability();

        if (availability < minAvailability) {
            System.out.println(String.format(STAT_MESSAGE_TEMPLATE, DATE_FORMAT.format(startIntervelDate), DATE_FORMAT.format(new Date(startIntervelDate.getTime() + intervalMs)), availability));
        }
    }
}